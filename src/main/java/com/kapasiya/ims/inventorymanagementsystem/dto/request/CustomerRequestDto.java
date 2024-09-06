package com.kapasiya.ims.inventorymanagementsystem.dto.request;

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
public class CustomerRequestDto {

    @NotBlank(message = "Customer Name cannot be blank")
    private String name;

    @NotBlank(message = "Customer Email cannot be blank")
    private String email;

    @NotBlank(message = "Customer Phone Number cannot be blank")
    private String phone;

    @NotBlank(message = "Customer Address cannot be blank")
    private String address;

    @NotBlank(message = "Order History List cannot be empty")
    private List<String> orderHistory;
}
