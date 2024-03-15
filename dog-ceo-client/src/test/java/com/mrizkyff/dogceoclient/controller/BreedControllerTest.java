package com.mrizkyff.dogceoclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.dto.client.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class BreedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean oddImageName(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        String fileNumber = fileName.replaceAll("\\D", "");
        return Integer.parseInt(fileNumber) % 2 != 0;
    }

    @Test
    void getAllBreedsWithSub() throws Exception {
        mockMvc.perform(
                get("/api/breed/list/all")
        ).andExpect(
                status().isOk()
        ).andDo(
                result -> {
                    String resultContent = result.getResponse().getContentAsString();
                    WebResponse<Map<String, Object>> webResponse = objectMapper.readValue(resultContent , WebResponse.class);
                    log.info("Web Response: {}", objectMapper.writeValueAsString(webResponse));
                    assertThat(webResponse.getStatus() , is("success"));
                    assertThat(webResponse.getMessage() , notNullValue());
                    assertTrue(webResponse.getMessage().containsKey("sheepdog-english"));
                    assertTrue(webResponse.getMessage().containsKey("sheepdog-shetland"));
                    AtomicInteger terrierSize = new AtomicInteger();
                    webResponse.getMessage().forEach(
                            (key, value) -> {
                                if (key.contains("terrier-")) {
                                    terrierSize.getAndIncrement();
                                }
                            }
                    );
                    assertEquals(23, terrierSize.get());
                }
        );
    }

    @Test
    void getBreedsImages() throws Exception {
        mockMvc.perform(
                get("/api/breed/shiba/images")
        ).andExpect(
                status().isOk()
        ).andDo(
                result -> {
                    String resultContent = result.getResponse().getContentAsString();
                    WebResponse<List<String>> webResponse = objectMapper.readValue(resultContent , WebResponse.class);
                    log.info("Web Response: {}" , objectMapper.writeValueAsString(webResponse));
                    assertThat(webResponse.getStatus() , is("success"));
                    assertThat(webResponse.getMessage() , notNullValue());
                    webResponse.getMessage().forEach(
                            imageUrl -> assertTrue(oddImageName(imageUrl))
                    );
                }
        );
    }

    @Test
    void getBreedNRandomImagesWithSub() throws Exception {
        mockMvc.perform(
                get("/api/breed/shiba/images/random/30")
        ).andExpect(
                status().isOk()
        ).andDo(
                result -> {
                    String resultContent = result.getResponse().getContentAsString();
                    WebResponse<List<String>> webResponse = objectMapper.readValue(resultContent , WebResponse.class);
                    log.info("Web Response: {}" , objectMapper.writeValueAsString(webResponse));
                    assertThat(webResponse.getStatus() , is("success"));
                    assertThat(webResponse.getMessage() , notNullValue());
                    webResponse.getMessage().forEach(
                            imageUrl -> assertTrue(oddImageName(imageUrl))
                    );
                }
        );
    }

    @Test
    void getSubBreeds() throws Exception {
        mockMvc.perform(
                get("/api/breed/terrier/list")
        ).andExpect(
                status().isOk()
        ).andDo(
                result -> {
                    String resultContent = result.getResponse().getContentAsString();
                    WebResponse<List<String>> webResponse = objectMapper.readValue(resultContent , WebResponse.class);
                    log.info("Web Response: {}" , objectMapper.writeValueAsString(webResponse));
                    assertThat(webResponse.getStatus() , is("success"));
                    assertThat(webResponse.getMessage() , notNullValue());
                    assertThat(webResponse.getMessage() , hasSize(23));
                }
        );
    }
}