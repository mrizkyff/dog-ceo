package com.mrizkyff.dogceorestful.repository;

import com.mrizkyff.dogceorestful.model.Breed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BreedRepository extends JpaRepository<Breed, UUID> {

    boolean existsByName(String name);

    @Query (
            value = """
                    SELECT * FROM breeds
                    WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
                     """,
            countQuery = """
                    SELECT COUNT(*) FROM breeds
                    WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
                     """,
            nativeQuery = true
    )
    Page<Breed> findAll(String name , Pageable pageable);
}
