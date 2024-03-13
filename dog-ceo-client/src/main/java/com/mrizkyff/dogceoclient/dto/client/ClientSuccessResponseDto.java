package com.mrizkyff.dogceoclient.dto.client;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSuccessResponseDto<T> {
    private T message;
    private String status;
}
