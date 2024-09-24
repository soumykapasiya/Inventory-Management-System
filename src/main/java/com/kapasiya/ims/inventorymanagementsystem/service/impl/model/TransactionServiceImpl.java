package com.kapasiya.ims.inventorymanagementsystem.service.impl.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.TransactionRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.TransactionResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Product;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Transaction;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.TransactionException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.TransactionMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.ProductRepo;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.TransactionRepo;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.TransactionService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ProductRepo productRepo;
    private final TransactionRepo transactionRepo;

    @Override
    @Transactional
    public CustomResponseDto<Void> addTransaction(TransactionRequestDto requestDto) {
        log.info("Adding transaction data...");
        try {
            Transaction transaction = TransactionMapper.toEntity(requestDto);

            List<String> productList = requestDto.getProducts();
            List<Product> products = productList.stream()
                    .parallel()
                    .flatMap(productName -> productRepo.findByProductName(productName).stream())
                    .toList();

            transaction.setProduct(products);
            transactionRepo.save(transaction);
            log.info("Transaction added successfully");
            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Transaction added successfully");
        } catch (TransactionException tx) {
            log.info("Error While Creating Transaction: {}", tx.getMessage());
            throw new TransactionException("Transaction could not be added");
        }
    }

    @Override
    public CustomResponseDto<List<TransactionResponseDto>> getAllTransactions() {
        log.info("Getting all transactions data...");
        try {
            Iterable<Transaction> transactions = transactionRepo.findAll();
            List<TransactionResponseDto> response = new ArrayList<>();

            for (Transaction transaction : transactions) {
                TransactionResponseDto temp = TransactionMapper.toRDto(transaction);
                response.add(temp);
            }
            return ResponseUtil.successDataResponse(HttpStatus.OK, "Getting all transactions data successfully", response);
        } catch (TransactionException tx) {
            log.info("Error While Getting all transactions: {}", tx.getMessage());
            throw new TransactionException("Transaction could not geted");
        }
    }

}
