package com.mrizkyff.dogceoclient.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.dto.client.ClientSuccessResponseDto;
import com.mrizkyff.dogceoclient.exception.InternalServerException;
import com.mrizkyff.dogceoclient.repository.BreedDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class BreedDataSourceImpl implements BreedDataSource {
    private static String HOST;
    private static String SCHEME;

    public BreedDataSourceImpl(RestTemplate restTemplate , ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Value ("${dog.ceo.api.url}")
    private void setDogApi(String dogApi) {
        HOST = dogApi;
    }

    @Value ("${dog.ceo.api.scheme}")
    private void setScheme(String scheme) {
        SCHEME = scheme;
    }

    @Value("${dog.ceo.breed.client.timeout}")
    private Long timeout;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private String buildApiUrl(String endpoint) {
        return UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .path(endpoint)
                .build()
                .toUriString();
    }

    private void handleErrorResponse(ResponseEntity<?> response) {
        if (response.getBody() == null || !response.getStatusCode().is2xxSuccessful()) {
            throw new InternalServerException("Internal Server Error : Dog CEO API is not available");
        }
    }


    @Override
    public Map<String, Object> findBreadsWithSub() {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/all") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response);
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public Map<String, Object> getRandomBreedWithSub() {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/all/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response);
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public Map<String, Object> getRandomNBreedsWithSub(int n) {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/all/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response);
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> getBreeds() {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response);
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

}
