package com.kapasiya.ims.inventorymanagementsystem.controller.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.LoginRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.request.UserRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.LoginResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.service.def.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User register successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<CustomResponseDto<Void>> register(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Register a new User");
        CustomResponseDto<Void> userResponseDto = authService.register(userRequestDto);
        log.info("Register successful");
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @Operation(summary = "Login User")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User login successfully",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CustomResponseDto.class)
                            )}),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<CustomResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("Login request");
        CustomResponseDto<LoginResponseDto> userResponseDto = authService.login(loginRequestDto);
        log.info("Login successful");
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}
