package com.mrizkyff.dogceorestful.repository;

import com.mrizkyff.dogceorestful.model.SubBreed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubBreedRepository extends JpaRepository<SubBreed, UUID> {

}
