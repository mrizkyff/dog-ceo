package com.mrizkyff.dogceoclient.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrizkyff.dogceoclient.exception.DataNotFoundException;
import com.mrizkyff.dogceoclient.repository.BreedDataSource;
import com.mrizkyff.dogceoclient.service.BreedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class BreedServiceImpl implements BreedService {

    private final BreedDataSource breedDataSource;

    private final ObjectMapper objectMapper;

    public BreedServiceImpl(BreedDataSource breedDataSource , ObjectMapper objectMapper) {
        this.breedDataSource = breedDataSource;
        this.objectMapper = objectMapper;
    }

    private boolean oddImageName(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        String fileNumber = fileName.replaceAll("[^\\d]", "");
        return Integer.parseInt(fileNumber) % 2 != 0;
    }

    @Override
    public Map<String, Object> getBreedsWithSub() {
        Map<String, Object> breedsWithSub = breedDataSource.findBreedsWithSub();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        Map<String, Object> result = new TreeMap<>();
        breedsWithSub.forEach((breed, subBreeds) -> {
            List<String> subBreedList = (List<String>) subBreeds;
            if (subBreedList.isEmpty()) {
                result.put(breed, null);
            }
            else {
                if (breed.equalsIgnoreCase("SHEEPDOG")) {
                    ((List<?>) subBreeds).forEach(subBreed -> result.put(breed + "-" + subBreed , List.of()));
                }
                if (breed.equalsIgnoreCase("TERRIER")) {
                    ((List<?>) subBreeds).forEach(subBreed -> {
                        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> breedDataSource.findSubBreedImages(breed, (String) subBreed))
                                .thenAccept(images -> {
                                    log.info("Worker from:{}", Thread.currentThread().getName());
                                    result.put(breed + "-" + subBreed , images);
                                });
                        futures.add(future);
                    });
                }
                if (!breed.equalsIgnoreCase("SHEEPDOG") && !breed.equalsIgnoreCase("TERRIER")) {
                    result.put(breed , subBreeds);
                }
            }
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();
        return result;
    }

    @Override
    public Map<String, Object> getRandomBreedWithSub() {
        return breedDataSource.findRandomBreedWithSub();
    }

    @Override
    public Map<String, Object> getRandomNBreedsWithSub(int n) {
        return breedDataSource.findRandomNBreedsWithSub(n);
    }

    @Override
    public List<String> getBreeds() {
        return breedDataSource.findBreeds();
    }

    @Override
    public String getRandomBreed() {
        return breedDataSource.findRandomBreed();
    }

    @Override
    public List<String> getRandomNBreeds(int n) {
        return breedDataSource.findRandomNBreeds(n);
    }

    @Override
    public List<String> getSubBreeds(String breed) {
        return breedDataSource.findSubBreeds(breed);
    }

    @Override
    public String getRandomSubBreeds(String breed) {
        return breedDataSource.findRandomSubBreeds(breed);
    }

    @Override
    public List<String> getRandomNSubBreeds(String breed , int n) {
        return breedDataSource.findRandomNSubBreeds(breed , n);
    }

    @Override
    public String getBreed(String breed) {
        return breedDataSource.findBreed(breed);
    }

    @Override
    public String getSubBreed(String breed , String subBreed) {
        return breedDataSource.findSubBreed(breed , subBreed);
    }

    @Override
    public String getRandomBreedImages(String breed) {
        return breedDataSource.findRandomBreedImages(breed);
    }

    @Override
    public List<String> getNRandomBreedImages(int n) {
        return breedDataSource.findNRandomBreedImages(n);
    }

    @Override
    public List<String> getBreedsImages(String breed) {
        List<String> breedsImages = breedDataSource.findBreedsImages(breed);
        if (breed.equalsIgnoreCase("SHIBA")) {
            return breedsImages.stream()
                    .filter(url -> {
                        String fileName = url.substring(url.lastIndexOf("/") + 1);
                        String fileNumber = fileName.replaceAll("[^\\d]", "");
                        return Integer.parseInt(fileNumber) % 2 != 0;
                    })
                    .toList();
        }
        return breedsImages;
    }

    @Override
    public String getBreedRandomImagesWithSub(String breed) {
        String image = breedDataSource.findBreedRandomImagesWithSub(breed);
        if (breed.equalsIgnoreCase("SHIBA")) {
            String fileName = image.substring(image.lastIndexOf("/") + 1);
            String fileNumber = fileName.replaceAll("[^\\d]", "");
            if (Integer.parseInt(fileNumber) % 2 != 0) {
                return image;
            }
            else {
                throw new DataNotFoundException("Image not found");
            }
        }
        return image;
    }

    @Override
    public List<String> getBreedNRandomImagesWithSub(String breed , int n) {
        List<String> images = breedDataSource.findBreedNRandomImagesWithSub(breed , n);
        if (breed.equalsIgnoreCase("SHIBA")) {
            return images.stream()
                    .filter(this::oddImageName)
                    .toList();
        }
        return images;
    }

    @Override
    public List<String> getSubBreedImages(String breed , String subBreed) {
        return breedDataSource.findSubBreedImages(breed , subBreed);
    }

    @Override
    public String getSubBreedRandomImagesWithSub(String breed , String subBreed) {
        return breedDataSource.findSubBreedRandomImagesWithSub(breed , subBreed);
    }

    @Override
    public List<String> getSubBreedNRandomImagesWithSub(String breed , String subBreed , int n) {
        return breedDataSource.findSubBreedNRandomImagesWithSub(breed , subBreed , n);
    }
}
