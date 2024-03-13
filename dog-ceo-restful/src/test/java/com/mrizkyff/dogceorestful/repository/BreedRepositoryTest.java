package com.mrizkyff.dogceorestful.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.mrizkyff.dogceorestful.model.Breed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback
class BreedRepositoryTest {

    @Autowired
    private BreedRepository breedRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void CreateBreed() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("SHIBA");
        breedRepository.save(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(breed));
        assertNotNull(breed);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void GetBreed() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("SHIBA");
        breedRepository.save(breed);


        Breed existingBreed = breedRepository.findById(breed.getId()).orElse(null);
        assertNotNull(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(existingBreed));
    }

    @Test
    void DeleteBreed() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("SHIBA");
        breedRepository.save(breed);

        breedRepository.deleteById(breed.getId());

        Breed deletedShiba = breedRepository.findById(breed.getId()).orElse(null);
        assertNull(deletedShiba);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(0, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void UpdateBreed() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("SHIBA");
        breedRepository.save(breed);

        Breed existingBreed = breedRepository.findById(breed.getId()).orElse(null);
        assertNotNull(existingBreed);
        existingBreed.setName("SHIBA INU");
        breedRepository.save(existingBreed);
        log.info("Breed: {}", objectMapper.writeValueAsString(existingBreed));

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }


}