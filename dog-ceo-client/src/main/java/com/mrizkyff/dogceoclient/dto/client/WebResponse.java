package com.mrizkyff.dogceoclient.dto.client;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse<T> {
    private T message;
    private String status;
}
