package com.mrizkyff.dogceoclient.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.dto.client.ClientSuccessResponseDto;
import com.mrizkyff.dogceoclient.exception.InternalServerException;
import com.mrizkyff.dogceoclient.repository.BreedDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Repository
@Slf4j
public class BreedDataSourceImpl implements BreedDataSource {
    private static String HOST;
    private static String SCHEME;

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

    public BreedDataSourceImpl(RestTemplate restTemplate , ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private String buildApiUrl(String endpoint) {
        return UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .path(endpoint)
                .build()
                .toUriString();
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
        if (response.getBody() == null) {
            throw new InternalServerException("Internal Server Error : Dog CEO API is not available");
        }
        if (!response.getBody().getStatus().equals("success")) {
            throw new InternalServerException("Internal Server Error : " + response.getBody().getMessage());
        }
        return response.getBody().getMessage();
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
        if (response.getBody() == null) {
            throw new InternalServerException("Internal Server Error : Dog CEO API is not available");
        }
        if (!response.getBody().getStatus().equals("success")) {
            throw new InternalServerException("Internal Server Error : " + response.getBody().getMessage());
        }
        return response.getBody().getMessage();
    }
}
