package com.kapasiya.ims.inventorymanagementsystem.service.def.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.LoginRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.request.UserRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.LoginResponseDto;

public interface AuthService {

    CustomResponseDto<Void> createRole(RoleRequestDto requestDTO);

    CustomResponseDto<Void> register(UserRequestDto requestDto);

    CustomResponseDto<LoginResponseDto> login(LoginRequestDto requestDto);

}
