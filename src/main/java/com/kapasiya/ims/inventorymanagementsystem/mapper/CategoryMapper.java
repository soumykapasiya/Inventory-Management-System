package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CategoryRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CategoryResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Category;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalArgumentException("Utility class CategoryMapper");
    }

    public static Category toEntity(CategoryRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }
        return Category.builder()
                .categoryName(requestDto.getCategoryName())
                .description(requestDto.getDescription())
                .build();
    }

    public static CategoryResponseDto toRDto(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryResponseDto.builder()
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build();
    }
}
