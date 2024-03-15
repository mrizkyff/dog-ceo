package com.mrizkyff.dogceorestful.dto.subBreed;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors ( chain = true )
public class SubBreedRequestDto implements Serializable {
    @NotBlank ( message = "Sub-breed name is required" )
    @NotNull ( message = "Sub-breed name is required" )
    private String name;

    @NotNull ( message = "Breed ID is required" )
    @JsonProperty ( "breedId" )
    private String breedId;

    private List<String> images;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}