package com.mrizkyff.dogceorestful.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table ( name = "breeds" )
@Entity
@SuperBuilder
@KeySpace("breeds")
public class Breed extends Auditable implements Serializable {
    @Column ( name = "name" , nullable = false , unique = true )
    private String name;

    @OneToMany ( mappedBy = "breed" , cascade = CascadeType.ALL , fetch = FetchType.EAGER )
    @JsonManagedReference
    private List<SubBreed> subBreeds;

    @TimeToLive ( unit = TimeUnit.MILLISECONDS )
    private Long ttl = 12000L;

    public void setName(String name) {
        this.name = name.toLowerCase().strip();
    }
}
