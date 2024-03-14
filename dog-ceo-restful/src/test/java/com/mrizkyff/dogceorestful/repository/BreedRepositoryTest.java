package com.mrizkyff.dogceorestful.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mrizkyff.dogceorestful.model.SubBreed;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import com.mrizkyff.dogceorestful.model.Breed;
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
class BreedRepositoryTest {

    @Autowired
    private BreedRepository breedRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());

    @Test
    void CreateBreedSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(breed));
        assertNotNull(breed);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void CreateBreedWithSubBreedSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");

        SubBreed subBreed1 = new SubBreed();
        subBreed1.setName("english");
        subBreed1.setCreatedDate(Instant.now());
        subBreed1.setLastModifiedDate(Instant.now());
        subBreed1.setBreed(breed);

        SubBreed subBreed2 = new SubBreed();
        subBreed2.setName("american");
        subBreed2.setCreatedDate(Instant.now());
        subBreed2.setLastModifiedDate(Instant.now());
        subBreed2.setBreed(breed);

        breed.setSubBreeds(List.of(subBreed1, subBreed2));
        breedRepository.save(breed);

        Breed existingBreed = breedRepository.findById(breed.getId()).orElse(null);
        assertNotNull(existingBreed);
        assertEquals("sheepdog", existingBreed.getName());
        assertEquals(2 , existingBreed.getSubBreeds().size());
        log.info("Breed: {}", objectMapper.writeValueAsString(existingBreed));
    }

    @Test
    void GetBreedSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);


        Breed existingBreed = breedRepository.findById(breed.getId()).orElse(null);
        assertNotNull(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(existingBreed));
    }

    @Test
    void DeleteBreedSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);

        breedRepository.deleteById(breed.getId());

        Breed deletedShiba = breedRepository.findById(breed.getId()).orElse(null);
        assertNull(deletedShiba);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(0, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void UpdateBreedSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);

        Breed existingBreed = breedRepository.findById(breed.getId()).orElse(null);
        assertNotNull(existingBreed);
        existingBreed.setName("sheepdog INU");
        breedRepository.save(existingBreed);
        log.info("Breed: {}", objectMapper.writeValueAsString(existingBreed));

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        assertEquals("sheepdog INU", breeds.getFirst().getName());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }

    @Test
    void GetAllBreedsSuccess() throws JsonProcessingException {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);

        Breed breed2 = new Breed();
        breed2.setName("sheepdog INU");
        breedRepository.save(breed2);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(2, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));
    }


}