package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
    private String productName;
    private String description;
    private Double price;
    private int stockLevel;

    @Field(type = FieldType.Object)
    private Category category;

    @Field(type = FieldType.Object)
    private Set<Supplier> suppliers;
}

