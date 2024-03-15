package com.mrizkyff.dogceorestful.repository;

import com.mrizkyff.dogceorestful.model.SubBreed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubBreedRepository extends JpaRepository<SubBreed, UUID> {

    boolean existsByNameAndBreed_Id(String name , UUID id);

    @Query(
            value = """
                    SELECT sb
                    FROM SubBreed sb
                    WHERE
                    (:name IS NULL OR sb.name LIKE %:name%)
            """,
            countQuery = """
                    SELECT COUNT(sb)
                    FROM SubBreed sb
                    WHERE
                    (:name IS NULL OR sb.name LIKE %:name%)
            """
    )
    Page<SubBreed> findAll(String name , Pageable pageable);
}
