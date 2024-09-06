package com.kapasiya.ims.inventorymanagementsystem.mapper;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CustomerRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomerResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Customer;

public class CustomerMapper {

    private CustomerMapper() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static Customer toEntity(CustomerRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        return Customer.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .phone(requestDto.getPhone())
                .address(requestDto.getAddress())
                .orderHistory(requestDto.getOrderHistory())
                .build();
    }

    public static CustomerResponseDto toRDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .orderHistory(customer.getOrderHistory())
                .build();
    }

}
