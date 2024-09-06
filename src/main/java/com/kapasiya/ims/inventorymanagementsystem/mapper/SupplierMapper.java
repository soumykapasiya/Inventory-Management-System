package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.SupplierRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Supplier;

import java.util.Set;

public class SupplierMapper {

    private SupplierMapper() {
        throw new IllegalArgumentException("Utility class cannot be instantiated");
    }

    public static Supplier toEntity(SupplierRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        return Supplier.builder()
                .supplierName(requestDto.getSupplierName())
                .supplierEmail(requestDto.getSupplierEmail())
                .supplierAddress(requestDto.getSupplierAddress())
                .supplierPhone(requestDto.getSupplierPhone())
                .products(Set.copyOf(requestDto.getProducts()))
                .build();
    }
}
