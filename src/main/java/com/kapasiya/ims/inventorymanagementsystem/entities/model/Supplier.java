package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "supplier")
public class Supplier extends BaseEntity {
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;

    @Email
    private String supplierEmail;
    private Set<String> products;
}
