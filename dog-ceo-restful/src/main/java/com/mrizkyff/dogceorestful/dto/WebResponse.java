package com.mrizkyff.dogceorestful.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {

    private Integer status;

    private String message;

    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Instant timestamp = Instant.now();

    private T data;

    public Instant getTimestamp() {
        return Instant.now();
    }
}
