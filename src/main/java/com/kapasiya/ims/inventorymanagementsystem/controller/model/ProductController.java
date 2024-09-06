package com.kapasiya.ims.inventorymanagementsystem.controller.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.ProductRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.ProductResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.ProductService;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create Product")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseDto<Void>> addProduct(@RequestBody ProductRequestDto requestDto) {
        log.info("Add product request: {}", requestDto);
        CustomResponseDto<Void> responseDto = productService.addProduct(requestDto);
        log.info("Add product response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get Products")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getAllProducts() {
        log.info("Get all products");
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.getAllProducts();
        log.info("All products response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get By Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getByCategory")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getProductsByCategory(@RequestParam String categoryName) {
        log.info("Get all products with category: {}", categoryName);
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.getProductsByCategory(categoryName);
        log.info("Product response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }


    @Operation(summary = "Get By Name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getByName")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getProductsByName(@RequestParam String productName) {
        log.info("Get all products with with name: {}", productName);
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.getProductByName(productName);
        log.info("Product by name response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get By Price Range")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getByPriceRange")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getProductsByPriceRange(@RequestParam double minPrice, double maxPrice) {
        log.info("Price Range Min{} And Max{}: ", minPrice, maxPrice);
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.findByPriceBetween(minPrice, maxPrice);
        log.info("Product By Price Range response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get By Category Price Less")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getByCategoryAndPriceLess")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getProductsByCategoryAndPriceLess(@RequestParam String categoryName, double minPrice) {
        log.info("Price Range Category{} And Min{}: ", categoryName, minPrice);
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.findByCategoryAndPriceLessThan(categoryName, minPrice);
        log.info("Product By CategoryName And Price Less response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get By Category Price Greater")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Products Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getByCategoryAndPriceGreater")
    public ResponseEntity<CustomResponseDto<List<ProductResponseDto>>> getProductsByCategoryAndPriceGreater(@RequestParam String categoryName, double maxPrice) {
        log.info("Price Range Category{} And Max{}: ", categoryName, maxPrice);
        CustomResponseDto<List<ProductResponseDto>> responseDto = productService.findByCategoryAndPriceGreaterThan(categoryName, maxPrice);
        log.info("Product By CategoryName And Price Greater response: {}", responseDto);
        return ResponseEntity.ok().body(responseDto);
    }

}
