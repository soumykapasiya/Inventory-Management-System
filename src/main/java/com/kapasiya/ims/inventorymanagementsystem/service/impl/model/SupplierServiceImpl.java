package com.kapasiya.ims.inventorymanagementsystem.service.impl.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.SupplierRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Supplier;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.SupplierException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.SupplierMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.SupplierRepo;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.SupplierService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;

    @Override
    public CustomResponseDto<Void> addSupplier(SupplierRequestDto requestDto){
        log.info("Creating new supplier with request: {}", requestDto);
        try {
            if (supplierRepo.existsBySupplierEmail(requestDto.getSupplierEmail())){
                log.info("Supplier with email {} already exists", requestDto.getSupplierEmail());
                throw new SupplierException("Supplier with email " + requestDto.getSupplierEmail() + " already exists");
            }
            Supplier supplier = SupplierMapper.toEntity(requestDto);
            supplierRepo.save(supplier);
            log.info("Supplier with request: {} created",requestDto);

            return ResponseUtil.successMessageResponse(HttpStatus.CREATED,"Supplier created successfully");
        }catch (SupplierException sx){
            log.info("Supplier with request: {} already exists", requestDto);
            throw new SupplierException("Supplier with email " + requestDto.getSupplierEmail() + " already exists");
        }catch (Exception ex){
            throw new SupplierException("Exception while creating supplier: "+ ex.getMessage());
        }
    }

}
