package com.mrizkyff.dogceorestful.controller;

import com.mrizkyff.dogceorestful.dto.WebResponse;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedRequestDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedResponseDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.service.SubBreedService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sub-breeds")
public class SubBreedController {

    private final SubBreedService subBreedService;

    public SubBreedController(SubBreedService subBreedService) {
        this.subBreedService = subBreedService;
    }

    @PostMapping(
            value = "",
            produces = "application/json",
            consumes = "application/json"
    )
    @Operation(
            summary = "Create Sub-Breed",
            tags = {"sub-breed"},
            description = """
                    This endpoint is used to create a new sub-breed.
                    Name & Breed id are required.
                    """
    )
    public WebResponse<SubBreedResponseDto> createSubBreed(@RequestBody @Valid SubBreedRequestDto subBreedRequestDto) {
        return WebResponse.<SubBreedResponseDto>builder()
                .data(subBreedService.mapToDto(
                        subBreedService.createSubBreed(
                                subBreedService.mapToEntity(subBreedRequestDto)
                        )
                ))
                .status(201)
                .message("SUCCESS")
                .build();
    }


    @GetMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Get Sub-Breed",
            tags = {"sub-breed"},
            description = """
                    This endpoint is used to get a sub-breed by id.
                    """
    )
    public WebResponse<SubBreedResponseDto> getSubBreed(@PathVariable UUID id) {
        return WebResponse.<SubBreedResponseDto>builder()
                .data(subBreedService.mapToDto(
                        subBreedService.getSubBreed(id)
                ))
                .status(200)
                .message("SUCCESS")
                .build();
    }


    @GetMapping(
            value = ""
    )
    @Operation(
            summary = "Get Sub-Breeds",
            tags = {"sub-breed"},
            description = """
                    This endpoint is used to get all sub-breeds.
                    """
    )
    public WebResponse<Page<SubBreedResponseDto>> getSubBreeds(
            @RequestParam (required = false) String name,
            Pageable pageable

    ) {
        return WebResponse.<Page<SubBreedResponseDto>>builder()
                .data(subBreedService.mapToPageDto(
                        subBreedService.getSubBreeds(name, pageable)
                ))
                .status(200)
                .message("SUCCESS")
                .build();
    }

    @PutMapping(
            value = "/{id}",
            produces = "application/json",
            consumes = "application/json"
    )
    @Operation(
            summary = "Update Sub-Breed",
            tags = {"sub-breed"},
            description = """
                    This endpoint is used to update a sub-breed by id.
                    """
    )
    public WebResponse<SubBreedResponseDto> updateSubBreed(@PathVariable UUID id, @RequestBody @Valid SubBreedUpdateRequestDto subBreedUpdateRequestDto) {
        return WebResponse.<SubBreedResponseDto>builder()
                .data(subBreedService.mapToDto(
                        subBreedService.updateSubBreed(
                                id,
                                subBreedService.mapToEntity(subBreedUpdateRequestDto)
                        )
                ))
                .status(200)
                .message("SUCCESS")
                .build();
    }


    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(
            summary = "Delete Sub-Breed",
            tags = {"sub-breed"},
            description = """
                    This endpoint is used to delete a sub-breed by id.
                    """
    )
public WebResponse<String> deleteSubBreed(@PathVariable UUID id) {
        subBreedService.deleteSubBreed(id);
        return WebResponse.<String>builder()
                .data("Sub-breed with id " + id + " deleted")
                .status(200)
                .message("SUCCESS")
                .build();
    }
}
