package com.mrizkyff.dogceorestful.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table ( name = "sub_breeds" )
@Entity
@Builder
public class SubBreed extends Auditable implements Serializable {
    @Column ( name = "name" , nullable = false , unique = true )
    private String name;

    @ManyToOne ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "breed_id" )
    private Breed breed;

}
