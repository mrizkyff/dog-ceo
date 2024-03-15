package com.mrizkyff.dogceorestful.service;

import com.mrizkyff.dogceorestful.dto.breed.BreedRequestDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedResponseDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.model.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BreedService {
    Breed createBreed(Breed breed);
    Breed updateBreed(UUID id , Breed breed);
    void deleteBreed(UUID breedId);
    Breed getBreed(UUID breedId);
    Page<Breed> getBreeds(String name , Pageable pageable);
    BreedResponseDto mapToDto(Breed breed);
    Breed mapToEntity(BreedUpdateRequestDto breedUpdateRequestDto);
    Breed mapToEntity(BreedRequestDto breedRequestDto);
    Page<BreedResponseDto> mapToPageDto(Page<Breed> breeds);

}
