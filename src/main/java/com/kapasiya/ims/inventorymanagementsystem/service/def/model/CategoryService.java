package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CategoryRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CategoryResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;

import java.util.List;

public interface CategoryService {
    CustomResponseDto<Void> addCategory(CategoryRequestDto requestDto);

    CustomResponseDto<List<CategoryResponseDto>> getAllCategories();
}
