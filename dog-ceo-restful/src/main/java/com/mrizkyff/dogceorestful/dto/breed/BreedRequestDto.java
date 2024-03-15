package com.mrizkyff.dogceorestful.dto.breed;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.Breed}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors ( chain = true )
public class BreedRequestDto implements Serializable {
    @NotBlank ( message = "Breed name is required" )
    @NotNull ( message = "Breed name is required" )
    private String name;
    private List<SubBreedDto> subBreeds;

    /**
     * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors ( chain = true )
    public static class SubBreedDto implements Serializable {
        private String name;
        private List<String> images;
    }

    public void setName(String name) {
        this.name = name.toLowerCase().strip();
    }
}