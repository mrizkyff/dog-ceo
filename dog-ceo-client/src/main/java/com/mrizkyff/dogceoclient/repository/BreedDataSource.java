package com.mrizkyff.dogceoclient.repository;

import java.util.List;
import java.util.Map;

public interface BreedDataSource {
    Map<String, Object> findBreadsWithSub();
    Map<String, Object> getRandomBreedWithSub();
    Map<String, Object> getRandomNBreedsWithSub(int n);
    List<String> getBreeds();
    String getRandomBreed();
    List<String> getRandomNBreeds(int n);
    List<String> getSubBreeds(String breed);
    String getRandomSubBreeds(String breed);
    List<String> getRandomNSubBreeds(String breed, int n);
    String getBreed(String breed);
    String getSubBreed(String breed, String subBreed);
    String getRandomBreedImages(String breed);
//    List<String> getBreedNRandomImages(String breed, int n);
//    List<String> getBreedsImages(String breed);
//    List<Breed> getBreedRandomImagesWithSub(String breed);
//    List<Breed> getBreedNRandomImagesWithSub(String breed, int n);

}
