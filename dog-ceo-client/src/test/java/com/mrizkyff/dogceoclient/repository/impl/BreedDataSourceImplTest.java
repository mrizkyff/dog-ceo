package com.mrizkyff.dogceoclient.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mrizkyff.dogceoclient.exception.DataNotFoundException;
import com.mrizkyff.dogceoclient.repository.BreedDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class BreedDataSourceImplTest {
    @Autowired
    private BreedDataSource breedDataSource;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());


    @Test
    void findBreadsWithSub() throws JsonProcessingException {
        Map<String, Object> breadsWithSub = breedDataSource.findBreadsWithSub();
        assertNotNull(breadsWithSub);
        log.info("Breed with sub : {}", objectMapper.writeValueAsString(breadsWithSub));
    }

    @Test
    void getRandomBreedWithSub() throws JsonProcessingException {
        Map<String, Object> randomBreedWithSub = breedDataSource.getRandomBreedWithSub();
        assertNotNull(randomBreedWithSub);
        log.info("Random breed with sub : {}", objectMapper.writeValueAsString(randomBreedWithSub));
    }

    @Test
    void getRandomNBreedsWithSub() throws JsonProcessingException {
        Map<String, Object> randomNBreedsWithSub = breedDataSource.getRandomNBreedsWithSub(3);
        assertNotNull(randomNBreedsWithSub);
        log.info("Random 3 breeds with sub : {}", objectMapper.writeValueAsString(randomNBreedsWithSub));
    }

    @Test
    void getBreeds() throws JsonProcessingException {
        List<String> breeds = breedDataSource.getBreeds();
        assertNotNull(breeds);
        log.info("Breeds : {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void getRandomBreed() throws JsonProcessingException {
        String randomBreed = breedDataSource.getRandomBreed();
        assertNotNull(randomBreed);
        log.info("Random breed : {}", objectMapper.writeValueAsString(randomBreed));
    }

    @Test
    void getRandomNBreeds() throws JsonProcessingException {
        List<String> randomNBreeds = breedDataSource.getRandomNBreeds(10);
        assertNotNull(randomNBreeds);
        log.info("Random 10 breeds : {}", objectMapper.writeValueAsString(randomNBreeds));
    }

    @Test
    void getSubBreeds() throws JsonProcessingException {
        List<String> subBreeds = breedDataSource.getSubBreeds("bulldog");
        assertNotNull(subBreeds);
        log.info("Sub breeds : {}", objectMapper.writeValueAsString(subBreeds));
    }

    @Test
    void getRandomSubBreeds() throws JsonProcessingException {
        String randomSubBreeds = breedDataSource.getRandomSubBreeds("bulldog");
        assertNotNull(randomSubBreeds);
        log.info("Random sub breeds : {}", objectMapper.writeValueAsString(randomSubBreeds));
    }

    @Test
    void getRandomNSubBreeds() throws JsonProcessingException {
        List<String> randomNSubBreeds = breedDataSource.getRandomNSubBreeds("bulldog", 2);
        assertNotNull(randomNSubBreeds);
        log.info("Random 2 sub breeds : {}", objectMapper.writeValueAsString(randomNSubBreeds));
    }

    @Test
    void getBreed() {
        assertThrowsExactly(DataNotFoundException.class, () -> {
            String breed = breedDataSource.getBreed("bulldog");
            assertNotNull(breed);
            log.info("Breed : {}", objectMapper.writeValueAsString(breed));
        });
    }

    @Test
    void getSubBreed() {
        assertThrowsExactly(DataNotFoundException.class, () -> {
            String subBreed = breedDataSource.getSubBreed("bulldog", "boston");
            assertNotNull(subBreed);
            log.info("Sub breed : {}", objectMapper.writeValueAsString(subBreed));
        });
    }

    @Test
    void getBreedRandomImages() throws JsonProcessingException {
        String breedRandomImages = breedDataSource.getRandomBreedImages("bulldog");
        assertNotNull(breedRandomImages);
        log.info("Breed random images : {}", objectMapper.writeValueAsString(breedRandomImages));
    }
}