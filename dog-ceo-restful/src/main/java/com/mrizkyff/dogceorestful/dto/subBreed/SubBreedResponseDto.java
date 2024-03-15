package com.mrizkyff.dogceorestful.dto.subBreed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors ( chain = true )
public class SubBreedResponseDto implements Serializable {
    private UUID id;
    private Instant createdDate;
    private Instant lastModifiedDate;
    private String name;
    private BreedDto breed;
    private List<String> images;

    /**
     * DTO for {@link com.mrizkyff.dogceorestful.model.Breed}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors ( chain = true )
    public static class BreedDto implements Serializable {
        private UUID id;
        private Instant createdDate;
        private Instant lastModifiedDate;
        private String name;
    }
}