package com.mrizkyff.dogceorestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DogCeoRestfulApplication {
    public static void main(String[] args) {
        SpringApplication.run(DogCeoRestfulApplication.class , args);
    }

}
