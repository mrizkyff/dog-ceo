package com.mrizkyff.dogceoclient.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.dto.client.ClientSuccessResponseDto;
import com.mrizkyff.dogceoclient.exception.DataNotFoundException;
import com.mrizkyff.dogceoclient.exception.InternalServerException;
import com.mrizkyff.dogceoclient.repository.BreedDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

    public BreedDataSourceImpl(RestTemplate restTemplate , ObjectMapper objectMapper , RestTemplate restTemplateTimeout2000 , RestTemplate restTemplateTimeout5000) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.restTemplateTimeout2000 = restTemplateTimeout2000;
        this.restTemplateTimeout5000 = restTemplateTimeout5000;
    }

    @Value ("${dog.ceo.api.url}")
    private void setDogApi(String dogApi) {
        HOST = dogApi;
    }

    @Value ("${dog.ceo.api.scheme}")
    private void setScheme(String scheme) {
        SCHEME = scheme;
    }

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final RestTemplate restTemplateTimeout2000;

    private final RestTemplate restTemplateTimeout5000;

    private String buildApiUrl(String endpoint) {
        return UriComponentsBuilder.newInstance()
                .scheme(SCHEME)
                .host(HOST)
                .path(endpoint)
                .build()
                .toUriString();
    }

    private void handleErrorResponse(ResponseEntity<?> response, String status) {
        if (response.getBody() == null) {
            throw new InternalServerException("Internal Server Error : Dog CEO API is not available");
        }
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new DataNotFoundException(response.getBody().toString());
        }
        if (!status.equals("success")) {
            throw new InternalServerException("Internal Server Error : Unsuccesful response from Dog CEO API");
        }
    }


    @Override
    public Map<String, Object> findBreedsWithSub() {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplateTimeout5000.exchange(
                buildApiUrl("/breeds/list/all") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public Map<String, Object> findRandomBreedWithSub() {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/all/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public Map<String, Object> findRandomNBreedsWithSub(int n) {
        ResponseEntity<ClientSuccessResponseDto<Map<String, Object>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/all/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findBreeds() {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findRandomBreed() {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findRandomNBreeds(int n) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/list/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findSubBreeds(String breed) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplateTimeout2000.exchange(
                buildApiUrl("/breed/" + breed + "/list") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findRandomSubBreeds(String breed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/list/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findRandomNSubBreeds(String breed , int n) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/list/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findBreed(String breed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                    buildApiUrl("/breed/" + breed) ,
                    HttpMethod.GET ,
                    null ,
                    new ParameterizedTypeReference<>() {
                    }
            );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findSubBreed(String breed , String subBreed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                    buildApiUrl("/breed/" + breed) ,
                    HttpMethod.GET ,
                    null ,
                    new ParameterizedTypeReference<>() {
                    }
            );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findRandomBreedImages(String breed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                buildApiUrl("/breeds/image/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findNRandomBreedImages(int n) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breeds/image/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findBreedsImages(String breed) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/images") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findBreedRandomImagesWithSub(String breed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/images/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findBreedNRandomImagesWithSub(String breed , int n) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/images/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findSubBreedImages(String breed , String subBreed) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/" + subBreed + "/images") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public String findSubBreedRandomImagesWithSub(String breed , String subBreed) {
        ResponseEntity<ClientSuccessResponseDto<String>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/" + subBreed + "/images/random") ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

    @Override
    public List<String> findSubBreedNRandomImagesWithSub(String breed , String subBreed , int n) {
        ResponseEntity<ClientSuccessResponseDto<List<String>>> response = restTemplate.exchange(
                buildApiUrl("/breed/" + breed + "/" + subBreed + "/images/random/" + n) ,
                HttpMethod.GET ,
                null ,
                new ParameterizedTypeReference<>() {
                }
        );
        handleErrorResponse(response, Objects.requireNonNull(response.getBody()).getStatus());
        return Objects.requireNonNull(response.getBody()).getMessage();
    }

}
