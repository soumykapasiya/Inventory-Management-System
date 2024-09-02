package com.kapasiya.ims.inventorymanagementsystem.exception.global;

import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomErrorResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.*;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CustomErrorResponseDto> userExceptionHandler(UserException e, WebRequest request) {
        CustomErrorResponseDto customErrorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(customErrorResponseDto);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<CustomErrorResponseDto> roleExceptionHandler(RoleException e, WebRequest request) {
        CustomErrorResponseDto customErrorResponse = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(customErrorResponse);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<CustomErrorResponseDto> productExceptionHandler(ProductException e, WebRequest request) {
        CustomErrorResponseDto customErrorResponse = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(customErrorResponse);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<CustomErrorResponseDto> categoryExceptionHandler(CategoryException e, WebRequest request) {
        CustomErrorResponseDto customErrorResponse = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(customErrorResponse);
    }

    @ExceptionHandler(SupplierException.class)
    public ResponseEntity<CustomErrorResponseDto> supplierExceptionHandler(SupplierException e, WebRequest request) {
        CustomErrorResponseDto customErrorResponse = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(customErrorResponse);
    }

}
