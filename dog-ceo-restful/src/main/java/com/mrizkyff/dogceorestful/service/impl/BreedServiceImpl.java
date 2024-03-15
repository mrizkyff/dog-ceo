package com.mrizkyff.dogceorestful.service.impl;

import com.mrizkyff.dogceorestful.dto.breed.BreedRequestDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedResponseDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.exception.BadRequestException;
import com.mrizkyff.dogceorestful.exception.DataNotFoundException;
import com.mrizkyff.dogceorestful.model.Breed;
import com.mrizkyff.dogceorestful.model.SubBreed;
import com.mrizkyff.dogceorestful.repository.BreedRepository;
import com.mrizkyff.dogceorestful.repository.SubBreedRepository;
import com.mrizkyff.dogceorestful.service.BreedService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;

    private final SubBreedRepository subBreedRepository;

    public BreedServiceImpl(BreedRepository breedRepository , SubBreedRepository subBreedRepository) {
        this.breedRepository = breedRepository;
        this.subBreedRepository = subBreedRepository;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "breed" , key = "#breed.id")},
            evict = {@CacheEvict(value = "breeds" , allEntries = true)}
    )
    public Breed createBreed(Breed breed) {
        if (breedRepository.existsByName(breed.getName())) {
            throw new BadRequestException("Breed with name " + breed.getName() + " already exists");
        }
        breed.getSubBreeds().forEach(
                subBreed -> subBreed.setBreed(breed)
        );
        breedRepository.save(breed);
        return breed;
    }

    @Override
    @Caching(
            put = {@CachePut(value = "breed" , key = "#id")},
            evict = {@CacheEvict(value = "breeds" , allEntries = true)}
    )
    public Breed updateBreed(UUID id , Breed breed) {
        Breed existingBreed = getBreed(id);
        if (breed.getName() != null) {
            if (!breed.getName().equals(existingBreed.getName())) {
                if (breedRepository.existsByName(breed.getName())) {
                    throw new BadRequestException("Breed with name " + breed.getName() + " already exists");
                }
            }
            existingBreed.setName(breed.getName());
        }

        subBreedRepository.deleteAll(existingBreed.getSubBreeds());
        existingBreed.setSubBreeds(new ArrayList<>());
        if (!breed.getSubBreeds().isEmpty()) {
            breed.getSubBreeds().forEach(
                    subBreed -> {
                        if (subBreed.getId() == null) {
                            subBreed.setBreed(existingBreed);
                            existingBreed.getSubBreeds().add(subBreed);
                        } else {
                            SubBreed existingSubBreed = existingBreed.getSubBreeds().stream()
                                    .filter(
                                            subBreed1 -> subBreed1.getId().equals(subBreed.getId())
                                    ).findFirst().orElseThrow(() -> new DataNotFoundException("Sub breed with id " + subBreed.getId() + " not found"));
                            existingSubBreed.setName(subBreed.getName());
                            existingSubBreed.setImages(subBreed.getImages());
                        }
                    }
            );
        }

        breedRepository.save(existingBreed);
        return existingBreed;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "breed" , key = "#breedId"),
                    @CacheEvict(value = "breeds" , allEntries = true)
            }
    )
    public void deleteBreed(UUID breedId) {
        Breed existingBreed = getBreed(breedId);
        breedRepository.delete(existingBreed);
    }

    @Override
    @Cacheable(value = "breed" , key = "#breedId")
    public Breed getBreed(UUID breedId) {
        return breedRepository.findById(breedId).orElseThrow(() -> new DataNotFoundException("Breed with id " + breedId + " not found"));
    }

    @Override
    @Cacheable(value = "breeds")
    public Page<Breed> getBreeds(String name , Pageable pageable) {
        return breedRepository.findAll(name , pageable);
    }

    @Override
    public BreedResponseDto mapToDto(Breed breed) {
        return new BreedResponseDto(
                breed.getId() ,
                breed.getCreatedDate() ,
                breed.getLastModifiedDate() ,
                breed.getName() ,
                breed.getSubBreeds().stream().map(
                        subBreed -> new BreedResponseDto.SubBreedDto(
                                subBreed.getId() ,
                                subBreed.getName() ,
                                subBreed.getImages()
                        )
                ).toList()
        );
    }

    @Override
    public Breed mapToEntity(BreedUpdateRequestDto breedUpdateRequestDto) {
        return Breed.builder()
                .name(breedUpdateRequestDto.getName())
                .subBreeds(
                        breedUpdateRequestDto.getSubBreeds().stream().map(
                                subBreedDto -> SubBreed.builder()
                                        .id(subBreedDto.getId())
                                        .createdDate(subBreedDto.getCreatedDate())
                                        .lastModifiedDate(subBreedDto.getLastModifiedDate())
                                        .name(subBreedDto.getName())
                                        .images(subBreedDto.getImages())
                                        .build()
                ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Breed mapToEntity(BreedRequestDto breedRequestDto) {
        return Breed.builder()
                .name(breedRequestDto.getName())
                .subBreeds(
                        breedRequestDto.getSubBreeds().stream().map(
                                subBreedDto -> SubBreed.builder()
                                        .name(subBreedDto.getName())
                                        .images(subBreedDto.getImages())
                                        .build()
                ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Page<BreedResponseDto> mapToPageDto(Page<Breed> breeds) {
        return breeds.map(this::mapToDto);
    }
}
