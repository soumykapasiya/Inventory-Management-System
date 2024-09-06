package com.kapasiya.ims.inventorymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Product NameCan not be blank")
    private String productName;

    @NotBlank(message = "Product Description Can not be blank")
    private String description;

    @NotBlank(message = "Product Price Can not be blank")
    private Double price;

    @NotBlank(message = "Product Stock Leve lCan not be blank")
    private int stockLevel;

    @NotBlank(message = "Product Category Name Can not be blank")
    private String categoryName;

    @NotBlank(message = "Product Supplier Emails Can not be null")
    private Set<String> supplierEmails;
}
