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

    @NotBlank(message = "Can not be blank")
    private String productName;
    @NotBlank(message = "Can not be blank")
    private String description;
    @NotBlank(message = "Can not be blank")
    private Double price;
    @NotBlank(message = "Can not be blank")
    private int stockLevel;
    @NotBlank(message = "Can not be blank")
    private String categoryName;
    @NotBlank(message = "Can not be blank")
    private Set<String> supplierEmails;
}
