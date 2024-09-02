package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.ProductRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    CustomResponseDto<Void> addProduct(ProductRequestDto requestDto);

    CustomResponseDto<List<ProductResponseDto>> getAllProducts();

    CustomResponseDto<List<ProductResponseDto>> getProductsByCategory(String CategoryName);

    CustomResponseDto<List<ProductResponseDto>> getProductByName(String productName);

    CustomResponseDto<List<ProductResponseDto>> findByPriceBetween(double minPrice, double maxPrice);

    CustomResponseDto<List<ProductResponseDto>> findByCategoryAndPriceLessThan(String categoryName, double minPrice);

    CustomResponseDto<List<ProductResponseDto>> findByCategoryAndPriceGreaterThan(String categoryName, double maxPrice);
}
