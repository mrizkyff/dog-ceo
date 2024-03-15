package com.mrizkyff.dogceorestful.dto.breed;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.Breed}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors ( chain = true )
public class BreedUpdateRequestDto implements Serializable {
    private String name;

    @NotEmpty ( message = "Sub breeds are required" )
    private List<SubBreedDto> subBreeds;

    /**
     * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors ( chain = true )
    public static class SubBreedDto implements Serializable {
        private UUID id;
        private Instant createdDate;
        private Instant lastModifiedDate;
        private String name;
        private List<String> images;
    }
}