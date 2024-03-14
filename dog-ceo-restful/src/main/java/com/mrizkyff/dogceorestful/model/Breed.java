package com.mrizkyff.dogceorestful.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table ( name = "breeds" )
@Entity
@Builder
public class Breed extends Auditable implements Serializable {
    private String name;

    @OneToMany ( mappedBy = "breed" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @JsonManagedReference
    private List<SubBreed> subBreeds;
}
