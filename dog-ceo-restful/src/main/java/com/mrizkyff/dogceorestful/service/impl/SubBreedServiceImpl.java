package com.mrizkyff.dogceorestful.service.impl;

import com.mrizkyff.dogceorestful.exception.BadRequestException;
import com.mrizkyff.dogceorestful.model.Breed;
import com.mrizkyff.dogceorestful.model.SubBreed;
import com.mrizkyff.dogceorestful.repository.BreedRepository;
import com.mrizkyff.dogceorestful.repository.SubBreedRepository;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedRequestDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedResponseDto;
import com.mrizkyff.dogceorestful.service.SubBreedService;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubBreedServiceImpl implements SubBreedService {

    private final SubBreedRepository subBreedRepository;

    private final BreedRepository breedRepository;

    public SubBreedServiceImpl(SubBreedRepository subBreedRepository , BreedRepository breedRepository) {
        this.subBreedRepository = subBreedRepository;
        this.breedRepository = breedRepository;
    }

    @Override
    public SubBreed createSubBreed(SubBreed subBreed) {
        breedRepository.findById(subBreed.getBreed().getId())
                .orElseThrow(() -> new BadRequestException("Breed with id " + subBreed.getBreed().getId() + " not found"));
        if (subBreedRepository.existsByNameAndBreed_Id(subBreed.getName() , subBreed.getBreed().getId())) {
            throw new BadRequestException("SubBreed with name " + subBreed.getName() + " already exists");
        }
        subBreedRepository.save(subBreed);
        return subBreed;
    }

    @Override
    public SubBreed getSubBreed(UUID subBreedId) {
        return subBreedRepository.findById(subBreedId)
                .orElseThrow(() -> new BadRequestException("Sub-breed with id " + subBreedId + " not found"));
    }

    @Override
    public Page<SubBreed> getSubBreeds(String name , Pageable pageable) {
        return subBreedRepository.findAll(name , pageable);
    }

    @Override
    public SubBreed updateSubBreed(UUID id , SubBreed subBreed) {
        SubBreed existingSubBreed = getSubBreed(id);
        if (subBreed.getName() != null) {
            if (!subBreed.getName().equals(existingSubBreed.getName())) {
                if (subBreedRepository.existsByNameAndBreed_Id(subBreed.getName() , existingSubBreed.getBreed().getId())) {
                    throw new BadRequestException("SubBreed with name " + subBreed.getName() + " already exists");
                }
                existingSubBreed.setName(subBreed.getName());
            }
        }
        existingSubBreed.setImages(subBreed.getImages());
        subBreedRepository.save(existingSubBreed);
        return existingSubBreed;
    }

    @Override
    public void deleteSubBreed(UUID subBreedId) {
        SubBreed existingSubBreed = getSubBreed(subBreedId);
        subBreedRepository.delete(existingSubBreed);
    }

    @Override
    public SubBreedResponseDto mapToDto(SubBreed subBreed) {
        return new SubBreedResponseDto(
                subBreed.getId() ,
                subBreed.getCreatedDate() ,
                subBreed.getLastModifiedDate() ,
                subBreed.getName() ,
                new SubBreedResponseDto.BreedDto(
                        subBreed.getBreed().getId() ,
                        subBreed.getBreed().getCreatedDate() ,
                        subBreed.getBreed().getLastModifiedDate() ,
                        subBreed.getBreed().getName()
                ) ,
                subBreed.getImages()
        );
    }

    @Override
    public SubBreed mapToEntity(SubBreedRequestDto subBreedRequestDto) {
        return SubBreed.builder()
                .name(subBreedRequestDto.getName())
                .images(subBreedRequestDto.getImages())
                .breed(Breed.builder().id(UUID.fromString(subBreedRequestDto.getBreedId())).build())
                .build();
    }

    @Override
    public SubBreed mapToEntity(SubBreedUpdateRequestDto subBreedUpdateRequestDto) {
        return SubBreed.builder()
                .name(subBreedUpdateRequestDto.getName())
                .images(subBreedUpdateRequestDto.getImages())
                .build();
    }

    @Override
    public Page<SubBreedResponseDto> mapToPageDto(Page<SubBreed> subBreeds) {
        return subBreeds.map(this::mapToDto);
    }
}
