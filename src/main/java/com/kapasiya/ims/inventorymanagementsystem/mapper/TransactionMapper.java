package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.TransactionRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.TransactionResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Transaction;

public class TransactionMapper {

    private TransactionMapper() {
        throw new IllegalArgumentException("Utility class cannot be instantiated");
    }

    public static Transaction toEntity(TransactionRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        return Transaction.builder()
                .amount(requestDto.getAmount())
                .description(requestDto.getDescription())
                .transactionType(requestDto.getTransactionType())
                .quantity(requestDto.getQuantity())
                .build();
    }

    public static TransactionResponseDto toRDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return TransactionResponseDto.builder()
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .transactionType(transaction.getTransactionType())
                .quantity(transaction.getQuantity())
                .products(transaction.getProduct())
                .build();
    }
}
