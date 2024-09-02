package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String id;
    private String accessToken;
    private String refreshToken;
    private String name;
    private String email;
    private String tokenExpiryTime;
}