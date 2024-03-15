package com.mrizkyff.dogceorestful.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedRequestDto;
import com.mrizkyff.dogceorestful.dto.subBreed.SubBreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.model.Breed;
import com.mrizkyff.dogceorestful.model.SubBreed;
import com.mrizkyff.dogceorestful.repository.BreedRepository;
import com.mrizkyff.dogceorestful.repository.SubBreedRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback
@SpringBootTest
@AutoConfigureMockMvc
class SubBreedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private SubBreedRepository subBreedRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final Breed breed = new Breed();

    @BeforeAll
    void beforeAll() {
        breed.setName("sheepdog");
        breed.setCreatedDate(Instant.now());
        breed.setLastModifiedDate(Instant.now());
        breedRepository.save(breed);
    }

    @AfterAll
    void afterAll() {
        subBreedRepository.deleteAll();
        breedRepository.deleteAll();
    }

    @Test
    void createSubBreed() throws Exception {
        SubBreedRequestDto subBreedRequestDto = new SubBreedRequestDto();
        subBreedRequestDto.setName("subBreedName");
        subBreedRequestDto.setBreedId(breed.getId().toString());

        mockMvc.perform(post("/api/v1/sub-breeds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subBreedRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.data.name").value("subbreedname"))
                .andExpect(jsonPath("$.data.breed.id").value(breed.getId().toString()));
    }

    @Test
    void getSubBreed() throws Exception {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreed.setBreed(breed);
        subBreed.setImages(List.of("image1", "image2"));
        subBreedRepository.save(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());

        mockMvc.perform(get("/api/v1/sub-breeds/" + subBreed.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.data.name").value("english"))
                .andExpect(jsonPath("$.data.breed.id").value(breed.getId().toString()));
    }

    @Test
    void getSubBreeds() throws Exception {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreed.setBreed(breed);
        subBreed.setImages(List.of("image1", "image2"));
        subBreedRepository.save(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());

        mockMvc.perform(get("/api/v1/sub-breeds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.data.content[0].name").value("english"))
                .andExpect(jsonPath("$.data.content[0].breed.id").value(breed.getId().toString()));

    }

    @Test
    void updateSubBreed() throws Exception {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreed.setBreed(breed);
        subBreed.setImages(List.of("image1", "image2"));
        subBreedRepository.save(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());

        SubBreedUpdateRequestDto subBreedUpdateRequestDto = new SubBreedUpdateRequestDto();
        subBreedUpdateRequestDto.setName("american");
        subBreedUpdateRequestDto.setImages(List.of("image3", "image4"));

        mockMvc.perform(put("/api/v1/sub-breeds/" + subBreed.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subBreedUpdateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andExpect(jsonPath("$.data.name").value("american"))
                .andExpect(jsonPath("$.data.breed.id").value(breed.getId().toString()))
                .andExpect(jsonPath("$.data.images[0]").value("image3"))
                .andExpect(jsonPath("$.data.images[1]").value("image4"));
    }

    @Test
    void deleteSubBreed() throws Exception {
        SubBreed subBreed = new SubBreed();
        subBreed.setName("english");
        subBreed.setCreatedDate(Instant.now());
        subBreed.setLastModifiedDate(Instant.now());
        subBreed.setBreed(breed);
        subBreed.setImages(List.of("image1", "image2"));
        subBreedRepository.save(subBreed);

        List<SubBreed> subBreeds = subBreedRepository.findAll();
        log.info("SubBreeds: {}", objectMapper.writeValueAsString(subBreeds));
        assertEquals(1, subBreeds.size());

        mockMvc.perform(delete("/api/v1/sub-breeds/" + subBreed.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("SUCCESS"));

        subBreeds = subBreedRepository.findAll();
        assertEquals(0, subBreeds.size());
    }
}