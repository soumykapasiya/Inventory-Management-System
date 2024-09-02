package com.kapasiya.ims.inventorymanagementsystem.repository.es;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends ElasticsearchRepository<Customer, String> {
}
