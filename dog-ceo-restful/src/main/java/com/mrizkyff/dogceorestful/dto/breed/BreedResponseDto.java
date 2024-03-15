package com.mrizkyff.dogceorestful.dto.breed;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.Breed}
 */
@Value
public class BreedResponseDto implements Serializable {
    UUID id;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    Instant createdDate;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    Instant lastModifiedDate;
    String name;
    List<SubBreedDto> subBreeds;

    /**
     * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
     */
    @Value
    public static class SubBreedDto implements Serializable {
        UUID id;
        String name;
        List<String> images;
    }
}