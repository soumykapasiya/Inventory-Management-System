package com.kapasiya.ims.inventorymanagementsystem.repository.es;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Supplier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends ElasticsearchRepository<Supplier, Long> {

    boolean existsBySupplierEmail(String email);

    Supplier findBySupplierEmail(String email);

}
