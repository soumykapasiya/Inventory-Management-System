package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private String categoryName;
    private String description;
}
