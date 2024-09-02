package com.kapasiya.ims.inventorymanagementsystem.controller.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CategoryRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CategoryResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.CategoryService;
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
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Create Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Category Added Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<CustomResponseDto<Void>> create(@RequestBody CategoryRequestDto requestDto) {
        log.info("Creating Category");
        CustomResponseDto<Void> responseDto = categoryService.addCategory(requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @Operation(summary = "Get All Category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Getting All Category Successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @GetMapping("/getAll")
    public ResponseEntity<CustomResponseDto<List<CategoryResponseDto>>> getAll() {
        log.info("Getting All Category");
        CustomResponseDto<List<CategoryResponseDto>> responseDto = categoryService.getAllCategories();
        return ResponseEntity.ok().body(responseDto);
    }

}
