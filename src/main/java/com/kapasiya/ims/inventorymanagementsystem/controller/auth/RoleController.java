package com.kapasiya.ims.inventorymanagementsystem.controller.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.auth.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "Creating Role")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Role created successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/createRole")
    public ResponseEntity<CustomResponseDto<Void>> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        log.info("Creating Role with name {}", roleRequestDto.getName());
        CustomResponseDto<Void> response = roleService.createRole(roleRequestDto);
        log.info("Role Created with: {}", response);
        return ResponseEntity.ok().body(response);
    }
}
