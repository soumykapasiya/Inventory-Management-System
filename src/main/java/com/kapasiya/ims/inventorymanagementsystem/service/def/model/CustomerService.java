package com.kapasiya.ims.inventorymanagementsystem.service.def.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CustomerRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomerResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    @Transactional
    CustomResponseDto<Void> addCustomer(CustomerRequestDto requestDto);

    CustomResponseDto<List<CustomerResponseDto>> getAllCustomers();
}
