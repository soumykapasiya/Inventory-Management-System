package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.auth.Role;

public class RoleMapper {

    private RoleMapper() {
        throw new IllegalArgumentException("Utility class cannot be instantiated");
    }

    public static Role toEntity(RoleRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }
        return Role.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();
    }

}
