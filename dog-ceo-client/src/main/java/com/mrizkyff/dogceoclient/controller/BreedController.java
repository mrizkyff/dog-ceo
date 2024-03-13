package com.mrizkyff.dogceoclient.controller;

import com.mrizkyff.dogceoclient.model.Breed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/breed")
public class BreedController {

    @GetMapping("/list/all")
    public List<Breed> getAllBreedsWithSub() {
        return null;
    }
}
