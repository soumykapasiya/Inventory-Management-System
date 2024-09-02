package com.kapasiya.ims.inventorymanagementsystem.service.impl.auth;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.RoleRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.auth.Role;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.RoleException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.RoleMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.mongo.RoleRepository;
import com.kapasiya.ims.inventorymanagementsystem.service.def.auth.RoleService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public CustomResponseDto<Void> createRole(RoleRequestDto requestDTO) {
        log.info("Creating Role with Request: {} ", requestDTO);
        try {
            if (roleRepository.existsByName(requestDTO.getName())) {
                log.info("Role with name {} already exists", requestDTO.getName());
                throw new RoleException("Role with name " + requestDTO.getName() + " already exists");
            }
            Role role = RoleMapper.toEntity(requestDTO);
            roleRepository.save(role);
            log.info("Role with name {} has been created", requestDTO.getName());

            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Role Created Successfully..");
        } catch (RoleException re) {
            log.error("Exception Wile Creating Role: {}", re.getMessage());
            throw re;
        } catch (Exception e) {
            log.error("Exception Occurred Wile Creating Role: {}", e.getMessage(), e);
            throw new RoleException("Error while creating Role: " + requestDTO.getName());
        }
    }
}
