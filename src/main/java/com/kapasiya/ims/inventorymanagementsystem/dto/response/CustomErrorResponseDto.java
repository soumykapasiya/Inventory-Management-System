package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponseDto {
    private LocalDateTime timeStamp;
    private String message;
    private int status;
    private String endpoint;
}
