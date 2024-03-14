package com.mrizkyff.dogceorestful.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mrizkyff.dogceorestful.model.SubBreed;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback
class SubBreedRepositoryTest {
    @Autowired
    private SubBreedRepository subBreedRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void CreateSubBreedSuccess() throws JsonProcessingException {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());
    }
    @Test
    void UpdateSubBreedSuccess() throws JsonProcessingException {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed);

        subBreed.setName("american");
        subBreed.setLastModifiedDate(Instant.now());

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());
        assertEquals("american", subBreeds.getFirst().getName());
    }
    @Test
    void DeleteSubBreedSuccess() {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed);

        subBreedRepository.delete(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        assertEquals(0, subBreeds.size());
    }
    @Test
    void GetSubBreedSuccess() throws JsonProcessingException {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed);

        SubBreed existingSubBreed = subBreedRepository.findById(subBreed.getId()).orElse(null);
        assertNotNull(existingSubBreed);
        log.info("SubBreed: {}", objectMapper.writeValueAsString(existingSubBreed));
    }
    @Test
    void GetAllSubBreedsSuccess() throws JsonProcessingException {
        SubBreed subBreed1 = new SubBreed();
        subBreed1.setName("english");
        subBreed1.setCreatedDate(Instant.now());
        subBreed1.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed1);

        SubBreed subBreed2 = new SubBreed();
        subBreed2.setName("american");
        subBreed2.setCreatedDate(Instant.now());
        subBreed2.setLastModifiedDate(Instant.now());
        subBreedRepository.save(subBreed2);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        assertEquals(2, subBreeds.size());
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
    }
}