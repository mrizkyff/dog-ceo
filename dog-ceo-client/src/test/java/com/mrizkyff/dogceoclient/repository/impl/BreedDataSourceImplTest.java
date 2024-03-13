package com.mrizkyff.dogceoclient.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
}