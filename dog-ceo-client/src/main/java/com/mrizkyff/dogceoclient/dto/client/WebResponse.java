package com.mrizkyff.dogceoclient.dto.client;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse {
    private Map<String, Object> message;
    private String status;
}
