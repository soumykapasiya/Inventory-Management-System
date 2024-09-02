package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.ProductRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.ProductResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Product;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Product toEntity(ProductRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        return Product.builder()
                .productName(requestDto.getProductName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .stockLevel(requestDto.getStockLevel())
                .build();
    }

    public static ProductResponseDto toRDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockLevel(product.getStockLevel())
                .category(product.getCategory())
                .suppliers(product.getSuppliers())
                .build();
    }
}
