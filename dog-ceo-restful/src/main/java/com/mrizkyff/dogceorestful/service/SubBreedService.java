package com.mrizkyff.dogceorestful.service;

import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedRequestDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedResponseDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.model.SubBreed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SubBreedService {
    SubBreed createSubBreed(SubBreed subBreed);
    SubBreed getSubBreed(UUID subBreedId);
    Page<SubBreed> getSubBreeds(String name , Pageable pageable);
    SubBreed updateSubBreed(UUID id, SubBreed subBreed);
    void deleteSubBreed(UUID subBreedId);
    SubBreedResponseDto mapToDto(SubBreed subBreed);
    SubBreed mapToEntity(SubBreedRequestDto subBreedRequestDto);
    SubBreed mapToEntity(SubBreedUpdateRequestDto subBreedUpdateRequestDto);
    Page<SubBreedResponseDto> mapToPageDto(Page<SubBreed> subBreeds);
}
