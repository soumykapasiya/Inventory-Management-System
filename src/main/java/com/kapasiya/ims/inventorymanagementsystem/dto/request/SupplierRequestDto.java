package com.kapasiya.ims.inventorymanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequestDto {

    @NotBlank(message = "Supplier Name can not be null...")
    private String supplierName;

    @NotBlank(message = "Supplier Address can not be null...")
    private String supplierAddress;

    @NotBlank(message = "Supplier Phone Number can not be null...")
    private String supplierPhone;

    @Email(message = "Email should be in format")
    @NotBlank(message = "Supplier Email can not be null...")
    private String supplierEmail;

    @NotBlank(message = "Product List can not be empty...")
    private List<String> products;

}
