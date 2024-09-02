package com.kapasiya.ims.inventorymanagementsystem.repository.es;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends ElasticsearchRepository<Category, String> {

    Optional<Category> findByCategoryName(String name);

    boolean existsByCategoryName(String name);

    boolean existsById(String CategoryId);
}
