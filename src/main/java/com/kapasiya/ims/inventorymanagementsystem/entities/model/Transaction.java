package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "category")
public class Transaction {

    @Id
    private String transactionId;
    private int quantity;
    private String transactionType;

    @Field(type = FieldType.Date)
    private String transactionDate;
    private String description;

    @Field(type = FieldType.Object)
    private Product product;

}
