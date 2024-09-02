package com.kapasiya.ims.inventorymanagementsystem.utils;

import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomErrorResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtil {

    private ResponseUtil() {
        throw new IllegalArgumentException("Utility class cannot be instantiated");
    }

    public static CustomErrorResponseDto buildErrorResponse(HttpStatus status, String message,String endpoint) {
        return CustomErrorResponseDto.builder()
                .message(message)
                .endpoint(endpoint)
                .status(status.value())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static CustomResponseDto<Void> successMessageResponse(HttpStatus status, String message) {
        return CustomResponseDto.<Void>builder()
                .message(message)
                .success(true)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .build();
    }

    public static <T> CustomResponseDto<T> successDataResponse(HttpStatus status, String message, T data) {
        return CustomResponseDto.<T>builder()
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .success(true)
                .message(message)
                .data(data)
                .build();
    }
}
