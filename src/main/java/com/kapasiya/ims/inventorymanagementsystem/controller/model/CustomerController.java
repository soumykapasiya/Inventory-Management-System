package com.kapasiya.ims.inventorymanagementsystem.controller.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CustomerRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomerResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.CustomerService;
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
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Create Customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Customer Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseDto<Void>> createCustomer(@RequestBody CustomerRequestDto requestDto) {
        log.info("Create Customer Request: {}", requestDto);
        CustomResponseDto<Void> response = customerService.addCustomer(requestDto);
        log.info("Create Customer Response: {}", response);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Get Customer")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Customer Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<CustomResponseDto<List<CustomerResponseDto>>> getAllCustomer() {
        log.info("Get All Customer Request");
        CustomResponseDto<List<CustomerResponseDto>> response = customerService.getAllCustomers();
        log.info("Get All Customer Response: {}", response);
        return ResponseEntity.ok().body(response);
    }
}
