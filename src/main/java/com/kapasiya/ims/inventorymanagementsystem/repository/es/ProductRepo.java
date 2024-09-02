package com.kapasiya.ims.inventorymanagementsystem.repository.es;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends ElasticsearchRepository<Product, String> {
    boolean existsByProductName(String productName);

    List<Product> findByProductNameContains(String productName);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByCategory_CategoryNameAndPriceGreaterThan(String category, double price);

    List<Product> findByCategory_CategoryNameAndPriceLessThan(String category, double minPrice);

    List<Product> findByCategory_CategoryName(String categoryName);

}
