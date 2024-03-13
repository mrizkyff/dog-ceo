package com.mrizkyff.dogceoclient.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().
                registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

}
