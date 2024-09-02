package com.kapasiya.ims.inventorymanagementsystem.service.def.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;

public interface RoleService {
    CustomResponseDto<Void> createRole(RoleRequestDto requestDTO);
}
