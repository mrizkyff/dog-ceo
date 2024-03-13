package com.mrizkyff.dogceoclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.service.BreedService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BreedServiceImplTest {

    @Autowired
    private BreedService breedService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBreedsWithSub() throws JsonProcessingException {
        Map<String, Object> breedsWithSub = breedService.getBreedsWithSub();
        assertTrue(breedsWithSub.containsKey("sheepdog-english"));
        assertTrue(breedsWithSub.containsKey("sheepdog-shetland"));
        assertEquals(0, ((List<?>) breedsWithSub.get("sheepdog-english")).size());
        assertEquals(0, ((List<?>) breedsWithSub.get("sheepdog-shetland")).size());
        log.info("Breed with sub : {}", objectMapper.writeValueAsString(breedsWithSub));
    }

    @Test
    void getRandomBreedWithSub() {
    }

    @Test
    void getRandomNBreedsWithSub() {
    }

    @Test
    void getBreeds() {
    }

    @Test
    void getRandomBreed() {
    }

    @Test
    void getRandomNBreeds() {
    }

    @Test
    void getSubBreeds() {
    }

    @Test
    void getRandomSubBreeds() {
    }

    @Test
    void getRandomNSubBreeds() {
    }

    @Test
    void getBreed() {
    }

    @Test
    void getSubBreed() {
    }

    @Test
    void getRandomBreedImages() {
    }

    @Test
    void getNRandomBreedImages() {
    }

    @Test
    void getBreedsImages() throws JsonProcessingException {
        List<String> breedsImages = breedService.getBreedsImages("shiba");
        log.info("Breeds imagess : {}", objectMapper.writeValueAsString(breedsImages));
    }

    @Test
    void getBreedRandomImagesWithSub() throws JsonProcessingException {
        String breedsImages = breedService.getBreedRandomImagesWithSub("shiba");
        log.info("Breeds imagess : {}", objectMapper.writeValueAsString(breedsImages));
    }

    @Test
    void getBreedNRandomImagesWithSub() {
        List<String> images = breedService.getBreedNRandomImagesWithSub("shiba" , 10);
        log.info("Breeds imagess : {}", images);
    }

    @Test
    void getSubBreedImages() {
    }

    @Test
    void getSubBreedRandomImagesWithSub() {
    }

    @Test
    void getSubBreedNRandomImagesWithSub() {
    }
}