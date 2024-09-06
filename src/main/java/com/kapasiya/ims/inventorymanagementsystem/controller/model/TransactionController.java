package com.kapasiya.ims.inventorymanagementsystem.controller.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.TransactionRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.ProductResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.TransactionResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Transaction;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create Transaction")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Transaction Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseDto<Void>> addTransaction(@RequestBody TransactionRequestDto requestDto) {
        log.info("Add transaction");
        CustomResponseDto<Void> responseDto = transactionService.addTransaction(requestDto);
        log.info("Add transaction responseDto: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get Transactions")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Transactions",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<CustomResponseDto<List<TransactionResponseDto>>> getAllTransactions() {
        log.info("Get all transactions");
        CustomResponseDto<List<TransactionResponseDto>> response = transactionService.getAllTransactions();
        log.info("Get all transactions response: {}", response);
        return ResponseEntity.ok().body(response);
    }

}
