package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private int quantity;
    private String transactionType;
    private String description;
    private double amount;
    private List<Product> products;
}
