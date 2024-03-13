package com.mrizkyff.dogceoclient.repository;

import java.util.List;
import java.util.Map;

public interface BreedDataSource {
    Map<String, Object> findBreadsWithSub();
    Map<String, Object> findRandomBreedWithSub();
    Map<String, Object> findRandomNBreedsWithSub(int n);
    List<String> findBreeds();
    String findRandomBreed();
    List<String> findRandomNBreeds(int n);
    List<String> findSubBreeds(String breed);
    String findRandomSubBreeds(String breed);
    List<String> findRandomNSubBreeds(String breed, int n);
    String findBreed(String breed);
    String findSubBreed(String breed, String subBreed);
    String findRandomBreedImages(String breed);
    List<String> findNRandomBreedImages(int n);
    List<String> findBreedsImages(String breed);
    String findBreedRandomImagesWithSub(String breed);
    List<String> findBreedNRandomImagesWithSub(String breed, int n);
    List<String> findSubBreedImages(String breed, String subBreed);
    String findSubBreedRandomImagesWithSub(String breed, String subBreed);
    List<String> findSubBreedNRandomImagesWithSub(String breed, String subBreed, int n);
}
