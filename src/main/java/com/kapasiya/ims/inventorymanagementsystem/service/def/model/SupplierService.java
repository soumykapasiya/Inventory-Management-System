package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.SupplierRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;

public interface SupplierService {
    CustomResponseDto<Void> addSupplier(SupplierRequestDto requestDto);
}
