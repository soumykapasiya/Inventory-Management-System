package com.kapasiya.ims.inventorymanagementsystem.service.impl.model;

import com.kapasiya.ims.inventorymanagementsystem.dto.request.ProductRequestDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.CustomResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.dto.response.ProductResponseDto;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Product;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Supplier;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.CategoryException;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.ProductException;
import com.kapasiya.ims.inventorymanagementsystem.exception.custom.SupplierException;
import com.kapasiya.ims.inventorymanagementsystem.mapper.ProductMapper;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.CategoryRepo;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.ProductRepo;
import com.kapasiya.ims.inventorymanagementsystem.repository.es.SupplierRepo;
import com.kapasiya.ims.inventorymanagementsystem.service.def.model.ProductService;
import com.kapasiya.ims.inventorymanagementsystem.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final SupplierRepo supplierRepo;

    @Override
    public CustomResponseDto<Void> addProduct(ProductRequestDto requestDto) {
        log.info("Adding product to the database with request: {}", requestDto);
        try {
            if (productRepo.existsByProductName(requestDto.getProductName())) {
                log.info("Product with name {} already exists", requestDto.getProductName());
                throw new ProductException("Product with name " + requestDto.getProductName() + " already exists");
            }
            Product product = ProductMapper.toEntity(requestDto);
            product.setCategory(categoryRepo.findByCategoryName(requestDto.getCategoryName()).orElseThrow(() -> new CategoryException("Category not found with name " + requestDto.getCategoryName())));

            Set<Supplier> suppliers = getSuppliers(requestDto);
            product.setSuppliers(suppliers);
            productRepo.save(product);
            return ResponseUtil.successMessageResponse(HttpStatus.CREATED, "Product added successfully");
        } catch (ProductException e) {
            log.info("Exception while adding product: {}", requestDto.getProductName());
            throw new ProductException(e.getMessage());
        }
    }

    private Set<Supplier> getSuppliers(ProductRequestDto requestDto) {
        Set<Supplier> suppliers = new HashSet<>();
        Set<String> setOfSuppliers = requestDto.getSupplierEmails();

        setOfSuppliers.forEach(item -> {
            Supplier temp = supplierRepo.findBySupplierEmail(item);
            if (temp == null) {
                log.info("Supplier with email {} does not exist", item);
                throw new SupplierException("Supplier not found with email: " + item);
            }
            suppliers.add(temp);
        });
        return suppliers;
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> getAllProducts() {
        log.info("Getting all products");
        try {
            Iterable<Product> listOfProduct = productRepo.findAll();
            List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
            for(Product product : listOfProduct) {
                productResponseDtoList.add(ProductMapper.toRDto(product));
            }
            return ResponseUtil.successDataResponse(HttpStatus.OK, "All products successfully", productResponseDtoList);
        } catch (ProductException e) {
            log.info("Exception while getting all products: {}", e.getMessage());
            throw new ProductException(e.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> getProductsByCategory(String categoryName) {
        log.info("Getting all products by Category {}", categoryName);
        try {
            List<Product> listOfProduct = productRepo.findByCategory_CategoryName(categoryName);
            List<ProductResponseDto> productResponseDtoList = listOfProduct.stream().parallel().map(ProductMapper::toRDto).toList();

            return ResponseUtil.successDataResponse(HttpStatus.OK, "Product By Category", productResponseDtoList);
        } catch (ProductException px) {
            log.info("Exception while getting products by category: {}", px.getMessage());
            throw new ProductException(px.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> getProductByName(String productName) {
        log.info("Getting Product With name: ");
        try {
            List<Product> listOfProducts = productRepo.findByProductNameContains(productName);
            List<ProductResponseDto> productResponseDtoList = listOfProducts.stream().parallel().map(ProductMapper::toRDto).toList();

            return ResponseUtil.successDataResponse(HttpStatus.OK, "Product By Name", productResponseDtoList);
        } catch (ProductException px) {
            log.info("Exception while getting products by name: {}", px.getMessage());
            throw new ProductException(px.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> findByPriceBetween(double minPrice, double maxPrice) {
        log.info("Finding Product By Price between {} and {}", minPrice, maxPrice);
        try {
            List<Product> listOfProducts = productRepo.findByPriceBetween(minPrice, maxPrice);
            List<ProductResponseDto> productResponseDtoList = listOfProducts.stream().parallel().map(ProductMapper::toRDto).toList();
            return ResponseUtil.successDataResponse(HttpStatus.OK, "Products By Price range", productResponseDtoList);
        } catch (ProductException px) {
            log.info("Exception while getting products by price: {}", px.getMessage());
            throw new ProductException(px.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> findByCategoryAndPriceLessThan(String categoryName, double minPrice) {
        log.info("Finding Product By Category and Price between {} and {}", categoryName, minPrice);
        try {
            List<Product> productList = productRepo.findByCategory_CategoryNameAndPriceLessThan(categoryName,minPrice);
            List<ProductResponseDto> productResponseDtoList = productList.stream().parallel().map(ProductMapper::toRDto).toList();
            return ResponseUtil.successDataResponse(HttpStatus.OK,"Product By Category and Price range", productResponseDtoList);
        } catch (ProductException px){
            log.info("Exception while getting products by Category and Price: {}", px.getMessage());
            throw new ProductException(px.getMessage());
        }
    }

    @Override
    public CustomResponseDto<List<ProductResponseDto>> findByCategoryAndPriceGreaterThan(String categoryName, double maxPrice) {
        log.info("Finding Product By Category and Price GreaterThan {} and {}", categoryName, maxPrice);
        try {
            List<Product> productList = productRepo.findByCategory_CategoryNameAndPriceGreaterThan(categoryName,maxPrice);
            List<ProductResponseDto> productResponseDtoList = productList.stream().parallel().map(ProductMapper::toRDto).toList();
            return ResponseUtil.successDataResponse(HttpStatus.OK,"Product By Category and Price range", productResponseDtoList);
        } catch (ProductException px){
            log.info("Exception while getting products by Category and Price greater than: {}", px.getMessage());
            throw new ProductException(px.getMessage());
        }
    }

}
