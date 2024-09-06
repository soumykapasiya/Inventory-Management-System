package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CustomerRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {

    @Transactional
    CustomResponseDto<Void> addCustomer(@RequestBody CustomerRequestDto requestDto);
}
