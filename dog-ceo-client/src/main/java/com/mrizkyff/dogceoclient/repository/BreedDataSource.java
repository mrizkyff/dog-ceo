package com.mrizkyff.dogceoclient.repository;

import java.util.List;
import java.util.Map;

public interface BreedDataSource {
    Map<String, Object> findBreadsWithSub();
    Map<String, Object> getRandomBreedWithSub();
    Map<String, Object> getRandomNBreedsWithSub(int n);
    List<String> getBreeds();
//    Breed getRandomBreed();
//    List<Breed> getRandomNBreeds(int n);
//    List<SubBreed> getSubBreeds(String breed);
//    List<SubBreed> getRandomSubBreeds(String breed);
//    List<SubBreed> getRandomNSubBreeds(String breed, int n);
//    Breed getBreed(String breed);
//    SubBreed getSubBreed(String breed, String subBreed);
//    List<String> getBreedRandomImages(String breed);
//    List<String> getBreedNRandomImages(String breed, int n);
//    List<String> getBreedsImages(String breed);
//    List<Breed> getBreedRandomImagesWithSub(String breed);
//    List<Breed> getBreedNRandomImagesWithSub(String breed, int n);

}
