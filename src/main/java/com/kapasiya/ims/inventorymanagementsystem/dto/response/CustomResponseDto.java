package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponseDto<T> {
    private String status;
    private int statusCode;
    private String message;
    private boolean success;
    private T data;
}
