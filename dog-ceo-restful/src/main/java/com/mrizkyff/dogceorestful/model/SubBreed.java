package com.mrizkyff.dogceorestful.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table ( name = "sub_breeds" )
@Entity
@SuperBuilder
public class SubBreed extends Auditable implements Serializable {
    @Column ( name = "name" , nullable = false )
    private String name;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn ( name = "breed_id" )
    @JsonBackReference
    private Breed breed;

    @ElementCollection (fetch = FetchType.EAGER)
    List<String> images;

}
