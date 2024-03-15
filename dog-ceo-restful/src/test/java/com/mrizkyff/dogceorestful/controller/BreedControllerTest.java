package com.mrizkyff.dogceorestful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceorestful.dto.WebResponse;
import com.mrizkyff.dogceorestful.dto.breed.BreedRequestDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedResponseDto;
import com.mrizkyff.dogceorestful.dto.breed.BreedUpdateRequestDto;
import com.mrizkyff.dogceorestful.model.Breed;
import com.mrizkyff.dogceorestful.repository.BreedRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@Rollback
class BreedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BreedRepository breedRepository;

    @Test
    void createBreed() throws Exception {
        BreedRequestDto breedRequestDto = new BreedRequestDto();
        breedRequestDto.setName("bulldog");

        mockMvc.perform(post("/api/v1/breeds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(breedRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status" , is(201)))
                .andExpect(jsonPath("$.message" , is("SUCCESS")))
                .andExpect(jsonPath("$.data.name" , is("bulldog")))
                .andExpect(
                        status().isCreated()
                ).andDo(
                        result -> {
                            String content = result.getResponse().getContentAsString();
                            TypeReference<WebResponse<BreedResponseDto>> typeReference = new TypeReference<>() {};
                            WebResponse<BreedResponseDto> webResponse = objectMapper.readValue(content, typeReference);
                            BreedResponseDto results = webResponse.getData();
                            log.info("Response : {}", objectMapper.writeValueAsString(results));
                            assertTrue(results.getSubBreeds().isEmpty());
                            assertNotNull(results.getCreatedDate());
                            assertNotNull(results.getLastModifiedDate());
                            assertEquals(201, webResponse.getStatus());
                            assertEquals("SUCCESS", webResponse.getMessage());
                    }
                );
    }

    @Test
    void getBreed() throws Exception {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(breed));
        assertNotNull(breed);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));

        mockMvc.perform(get("/api/v1/breeds/" + breed.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status" , is(200)))
                .andExpect(jsonPath("$.message" , is("SUCCESS")))
                .andExpect(jsonPath("$.data.name" , is("sheepdog")))
                .andExpect(
                        status().isOk()
                ).andDo(
                        result -> {
                            String content = result.getResponse().getContentAsString();
                            TypeReference<WebResponse<BreedResponseDto>> typeReference = new TypeReference<>() {};
                            WebResponse<BreedResponseDto> webResponse = objectMapper.readValue(content, typeReference);
                            BreedResponseDto results = webResponse.getData();
                            log.info("Response : {}", objectMapper.writeValueAsString(results));
                            assertTrue(results.getSubBreeds().isEmpty());
                            assertNotNull(results.getCreatedDate());
                            assertNotNull(results.getLastModifiedDate());
                            assertEquals(200, webResponse.getStatus());
                            assertEquals("SUCCESS", webResponse.getMessage());
                        }
                );
    }

    @Test
    void getBreeds() throws Exception {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);

        Breed breed2 = new Breed();
        breed2.setName("sheepdog INU");
        breedRepository.save(breed2);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(2, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));

        mockMvc.perform(get("/api/v1/breeds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status" , is(200)))
                .andExpect(jsonPath("$.message" , is("SUCCESS")))
                .andExpect(jsonPath("$.data.content" , hasSize(2)))
                .andExpect(
                        status().isOk()
                ).andDo(
                        result -> {
                            String content = result.getResponse().getContentAsString();
                            TypeReference<WebResponse<Object>> typeReference = new TypeReference<>() {};
                            WebResponse<Object> pageWebResponse = objectMapper.readValue(content , typeReference);
                            log.info("Response : {}", objectMapper.writeValueAsString(pageWebResponse));
                            Object data = pageWebResponse.getData();
                            log.info("Data : {}", objectMapper.writeValueAsString(data));
                        }
                );

    }

    @Test
    void updateBreed() throws Exception {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);
        log.info("Breed: {}", objectMapper.writeValueAsString(breed));
        assertNotNull(breed);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", objectMapper.writeValueAsString(breeds));

        BreedUpdateRequestDto breedUpdateRequestDto = new BreedUpdateRequestDto();
        breedUpdateRequestDto.setName("sheepdog INU");

        mockMvc.perform(put("/api/v1/breeds/" + breed.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(breedUpdateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status" , is(200)))
                .andExpect(jsonPath("$.message" , is("SUCCESS")))
                .andExpect(jsonPath("$.data.name" , is("sheepdog inu")))
                .andExpect(
                        status().isOk()
                ).andDo(
                        result -> {
                            String content = result.getResponse().getContentAsString();
                            TypeReference<WebResponse<BreedResponseDto>> typeReference = new TypeReference<>() {};
                            WebResponse<BreedResponseDto> webResponse = objectMapper.readValue(content, typeReference);
                            BreedResponseDto results = webResponse.getData();
                            log.info("Response : {}", objectMapper.writeValueAsString(results));
                            assertTrue(results.getSubBreeds().isEmpty());
                            assertNotNull(results.getCreatedDate());
                            assertNotNull(results.getLastModifiedDate());
                            assertEquals(200, webResponse.getStatus());
                            assertEquals("SUCCESS", webResponse.getMessage());
                        }
                );
    }

    @Test
    void deleteBreed() throws Exception {
        Breed breed = new Breed();
        breed.setName("sheepdog");
        breedRepository.save(breed);
        log.info("Breed: {}", breed);
        assertNotNull(breed);

        List<Breed> breeds = breedRepository.findAll();
        assertEquals(1, breeds.size());
        log.info("Breeds: {}", breeds);

        mockMvc.perform(delete("/api/v1/breeds/" + breed.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status" , is(200)))
                .andExpect(jsonPath("$.message" , is("SUCCESS")))
                .andExpect(
                        status().isOk());
    }
}