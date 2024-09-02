package com.kapasiya.ims.inventorymanagementsystem.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto {
    private String id;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String createdBy;
    private String updatedBy;
}