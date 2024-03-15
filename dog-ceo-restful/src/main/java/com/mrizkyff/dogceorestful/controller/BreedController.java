package com.mrizkyff.dogceorestful.controller;

import com.mrizkyff.dogceorestful.dto.WebResponse;
import com.mrizkyff.dogceorestful.dto.breed.BreedRequestDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedResponseDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.service.BreedService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ( "/api/v1/breeds" )
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @PostMapping(
            value = "",
            produces = "application/json",
            consumes = "application/json"
    )
    @Operation (
            summary = "Create Breed",
            tags = { "breed" },
            description = """
                    This endpoint is used to create a new breed.
                    Name is required.
                    """
    )
    public WebResponse<BreedResponseDto> createBreed(@RequestBody @Valid BreedRequestDto breedRequestDto) {
        return WebResponse.<BreedResponseDto>builder()
                .data(breedService.mapToDto(
                        breedService.createBreed(
                                breedService.mapToEntity(breedRequestDto)
                        )
                ))
                .status(201)
                .message("SUCCESS")
                .build();
    }

    @GetMapping(
            value = "/{id}"
    )
    @Operation (
            summary = "Get Breed",
            tags = { "breed" },
            description = """
                    This endpoint is used to get a breed by id.
                    """
    )
    public WebResponse<BreedResponseDto> getBreed(@PathVariable("id") UUID id) {
        return WebResponse.<BreedResponseDto>builder()
                .data(breedService.mapToDto(
                        breedService.getBreed(id)
                ))
                .status(200)
                .message("SUCCESS")
                .build();
    }

    @GetMapping(
            value = ""
    )
    @Operation (
            summary = "Get Breeds",
            tags = { "breed" },
            description = """
                    This endpoint is used to get all breeds.
                    Filter available by name.
                    Pagination available.
                    """
    )
    public WebResponse<Page<BreedResponseDto>> getBreeds(@RequestParam(required = false) String name, Pageable pageable) {
        return WebResponse.<Page<BreedResponseDto>>builder()
                .data(breedService.mapToPageDto(
                        breedService.getBreeds(
                                name ,
                                pageable
                        )
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
    @Operation (
            summary = "Update Breed",
            tags = { "breed" },
            description = """
                    This endpoint is used to update a breed by id.
                    """
    )
    public WebResponse<BreedResponseDto> updateBreed(@PathVariable("id") UUID id , @RequestBody @Valid BreedUpdateRequestDto breedRequestDto) {
        return WebResponse.<BreedResponseDto>builder()
                .data(breedService.mapToDto(
                        breedService.updateBreed(
                                id ,
                                breedService.mapToEntity(breedRequestDto)
                        )
                ))
                .status(200)
                .message("SUCCESS")
                .build();
    }

    @DeleteMapping(
            value = "/{id}",
            produces = "application/json",
            consumes = "application/json"
    )
    @Operation (
            summary = "Delete Breed",
            tags = { "breed" },
            description = """
                    This endpoint is used to delete a breed by id.
                    """
    )
    public WebResponse<BreedResponseDto> deleteBreed(@PathVariable("id") UUID id) {
        breedService.deleteBreed(id);
        return WebResponse.<BreedResponseDto>builder()
                .status(200)
                .message("SUCCESS")
                .build();
    }

}
