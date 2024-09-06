package com.kapasiya.ims.inventorymanagementsystem.service.impl.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CustomerRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Customer;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.CustomerException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.CustomerMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.CustomerRepo;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.CustomerService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    @Transactional
    public CustomResponseDto<Void> addCustomer(@RequestBody CustomerRequestDto requestDto) {
        log.info("Adding Customer With Request: {}", requestDto);
        try {
            if (customerRepo.existsByEmail(requestDto.getEmail())) {
                log.info("Email Already Exists");
                throw new CustomerException("Email Already Exists");
            }
            Customer customer = CustomerMapper.toEntity(requestDto);
            customerRepo.save(customer);

            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Customer Added Successfully");
        } catch (CustomerException cx) {
            log.info("Exception While Creating Customer: {}", cx.getMessage());
            throw new CustomerException(cx.getMessage());
        }
    }




}
