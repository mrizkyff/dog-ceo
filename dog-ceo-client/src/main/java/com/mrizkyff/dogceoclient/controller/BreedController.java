package com.mrizkyff.dogceoclient.controller;

import com.mrizkyff.dogceoclient.model.Breed;
import com.mrizkyff.dogceoclient.service.BreedService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/breed")
public class BreedController {

    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/list/all")
    public Map<String, Object> getAllBreedsWithSub() {
        return breedService.getBreedsWithSub();
    }
}
