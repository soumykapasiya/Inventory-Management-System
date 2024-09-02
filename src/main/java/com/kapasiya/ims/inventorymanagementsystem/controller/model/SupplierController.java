package com.kapasiya.ims.inventorymanagementsystem.controller.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.SupplierRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(summary = "Create Supplier")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Supplier Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseDto<Void>> createSupplier(@RequestBody SupplierRequestDto requestDto) {
        log.info("Creating Supplier with request: {}", requestDto);
        CustomResponseDto<Void> responseDto = supplierService.addSupplier(requestDto);
        log.info("Supplier added successfully: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
