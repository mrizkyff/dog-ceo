package com.mrizkyff.dogceoclient.controller;

import com.mrizkyff.dogceoclient.service.BreedService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/breed")
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/list/all")
    @Operation (
            summary = "Get All Breeds Including Sub Breeds",
            tags = { "breed"},
            description = """
                    This endpoint is answer for question:
                    - Timeout 5000ms in restTemplate bean
                    - Using lambda
                    - Extract "sheepdog"
                    - Extract "terrier" and fetch images using completable future
                    """
    )
    public Map<String, Object> getAllBreedsWithSub() {
        return breedService.getBreedsWithSub();
    }

    @GetMapping("/{breed}/images")
    @Operation (
            summary = "Get All Breed Images",
            tags = { "breed"},
            description = """
                    This endpoint is answer for question:
                    - Show only odd images from "shiba" breed
                    - Lambda function
                    """
    )
    public List<String> getBreedsImages(
            @PathVariable("breed") String breed
    ) {
        return breedService.getBreedsImages(breed);
    }


    @GetMapping("/{breed}/images/random/{n}")
    @Operation (
            summary = "Get N Random Images From a Breed",
            tags = { "breed"},
            description = """
                    This endpoint is answer for question:
                    - Show only odd images from "shiba" breed
                    - Method reference
                    """
    )
    public List<String> getBreedNRandomImagesWithSub(
            @PathVariable("breed") String breed ,
            @PathVariable ("n") int n
    ) {
        return breedService.getBreedNRandomImagesWithSub(breed , n);
    }


    @GetMapping("/{breed}/list")
    @Operation (
            summary = "List Sub Breeds",
            tags = { "breed"},
            description = """
                    This endpoint is answer for question:
                    - Timeout 2000ms in restTemplate bean
                    """
    )
    public List<String> getSubBreeds(
            @PathVariable ( "breed" ) String breed
    ) {
        return breedService.getSubBreeds(breed);
    }
}
