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
public class TransactionRequestDto {

    @NotBlank(message = "Transaction Quantity can not be blank")
    private int quantity;

    @NotBlank(message = "Transaction Type can not be blank")
    private String transactionType;

    @NotBlank(message = "Transaction Description can not be blank")
    private String description;

    @NotBlank(message = "Transaction Amount can not be blank")
    private double amount;

    @NotBlank(message = "Transaction Product Name List can not be empty")
    private List<String> products;
}
