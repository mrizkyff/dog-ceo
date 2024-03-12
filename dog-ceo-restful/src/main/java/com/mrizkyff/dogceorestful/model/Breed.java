package com.mrizkyff.dogceorestful.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table ( name = "breeds" )
@Entity
@Builder
public class Breed {
    @Id
    @GeneratedValue ( strategy = GenerationType.UUID )
    private UUID id;

    private String name;
}
