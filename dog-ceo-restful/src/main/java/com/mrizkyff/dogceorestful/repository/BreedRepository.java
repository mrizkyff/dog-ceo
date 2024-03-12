package com.mrizkyff.dogceorestful.repository;

import com.mrizkyff.dogceorestful.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BreedRepository extends JpaRepository<Breed, UUID> {

}
