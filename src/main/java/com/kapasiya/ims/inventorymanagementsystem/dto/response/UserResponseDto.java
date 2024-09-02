package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String profileUrl;
}
