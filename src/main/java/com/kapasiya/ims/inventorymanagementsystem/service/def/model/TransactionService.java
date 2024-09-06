package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.TransactionRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.TransactionResponseDto;

import java.util.List;

public interface TransactionService {
    CustomResponseDto<Void> addTransaction(TransactionRequestDto requestDto);

    CustomResponseDto<List<TransactionResponseDto>> getAllTransactions();
}
