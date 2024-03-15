package com.mrizkyff.dogceorestful.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceorestful.model.Breed;
import com.mrizkyff.dogceorestful.model.SubBreed;
import com.mrizkyff.dogceorestful.service.BreedService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback
class BreedServiceImplTest {

    @Autowired
    private BreedService breedService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getBreeds() throws JsonProcessingException {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setImages(List.of(
                "https://images.dog.ceo/breeds/spaniel-irish/n02102973_1003.jpg",
                "https://images.dog.ceo/breeds/spaniel-irish/n02102973_1004.jpg"
        ));

        Breed breed = new Breed();
        breed.setName("sheepdog2ss");
        breed.setSubBreeds(List.of(subBreed));
        subBreed.setBreed(breed);

        breedService.createBreed(breed);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Breed> breeds = breedService.getBreeds(null , pageRequest);
        assertNotNull(breeds);
        assertEquals(1 , breeds.getTotalElements());
        log.info("breeds: {}", objectMapper.writeValueAsString(breeds.getContent()));
    }
}