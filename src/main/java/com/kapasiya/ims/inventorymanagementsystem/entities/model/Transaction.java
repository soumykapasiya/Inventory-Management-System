package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "transaction")
public class Transaction extends BaseEntity {

    private int quantity;
    private String transactionType;
    private String description;
    private double amount;

    @Field(type = FieldType.Object)
    private List<Product> product;
}
