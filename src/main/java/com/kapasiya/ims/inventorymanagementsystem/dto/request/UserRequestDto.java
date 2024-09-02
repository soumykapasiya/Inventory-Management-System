package com.kapasiya.ims.inventorymanagementsystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Name can not be null")
    private String name;

    @Email(message = "Invalid Email format")
    @NotBlank(message = "Email Can Not Be Null")
    private String email;

    @NotBlank(message = "Password Can not be null")
    private String password;

    @NotBlank(message = "Phone can not null")
    @Size(min = 10, max = 12)
    private String phone;

    @NotBlank(message = "Role Id can not be null")
    private String roleId;

    private String createdBy;
}