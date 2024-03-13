package com.mrizkyff.dogceoclient.model;

import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Breed {
    private String name;

    ArrayList<SubBreed> subBreeds;
}
