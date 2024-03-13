package com.mrizkyff.dogceoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DogCeoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogCeoClientApplication.class , args);
    }

}
