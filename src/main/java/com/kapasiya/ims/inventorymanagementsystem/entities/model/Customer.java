package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "customers")
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String address;
    private List<String> orderHistory;
}