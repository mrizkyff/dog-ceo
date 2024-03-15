package com.mrizkyff.dogceorestful.dto.subBreed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mrizkyff.dogceorestful.model.SubBreed}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors ( chain = true )
public class SubBreedUpdateRequestDto implements Serializable {
    private String name;

    private List<String> images;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}