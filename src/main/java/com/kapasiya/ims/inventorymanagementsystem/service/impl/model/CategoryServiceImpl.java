package com.kapasiya.ims.inventorymanagementsystem.service.impl.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.CategoryRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CategoryResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Category;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.CategoryException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.CategoryMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.CategoryRepo;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.CategoryService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    @Transactional
    public CustomResponseDto<Void> addCategory(CategoryRequestDto requestDto) {
        log.info("Adding Category With Request: {} ", requestDto);
        try {
            if (categoryRepo.existsByCategoryName(requestDto.getCategoryName())) {
                log.info("Category already exists with name : {}", requestDto.getCategoryName());
                throw new CategoryException("Category Name Already Exists");
            }
            Category category = CategoryMapper.toEntity(requestDto);
            categoryRepo.save(category);
            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Category Added Successfully");
        } catch (CategoryException cx) {
            log.info("Exception while adding the category: {}", requestDto.getCategoryName());
            throw new CategoryException("Category Name Already Exists" + cx.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<CategoryResponseDto>> getAllCategories() {
        log.info("Getting All Category List");
        try {
            Iterable<Category> categoryList = categoryRepo.findAll();
            List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
            for (Category category : categoryList) {
                CategoryResponseDto categoryResponseDto = CategoryMapper.toRDto(category);
                categoryResponseDtoList.add(categoryResponseDto);
            }
            return ResponseUtil.successDataResponse(HttpStatus.OK, "Getting ALl Data", categoryResponseDtoList);
        } catch (CategoryException cx) {
            log.info("Exception while getting all categories: {}", cx.getMessage());
            throw new CategoryException("Getting Error while getting all categories" + cx.getMessage());
        }
    }

    @Override
    @Transactional
    public CustomResponseDto<Void> deleteCategory(String categoryId) {
        log.info("Deleting Category : {}", categoryId);
        try {
            if (!categoryRepo.existsById(categoryId)) {
                log.info("Category does not exist with id : {}", categoryId);
                throw new CategoryException("Category does not exist with id : " + categoryId);
            }
            categoryRepo.deleteById(categoryId);
            return ResponseUtil.successMessageResponse(HttpStatus.NO_CONTENT, "Category Deleted Successfully");
        } catch (CategoryException cx) {
            log.info("Exception while deleting the category: {}", categoryId);
            throw new CategoryException("Error While creating category: " + cx.getMessage());
        }
    }


}
