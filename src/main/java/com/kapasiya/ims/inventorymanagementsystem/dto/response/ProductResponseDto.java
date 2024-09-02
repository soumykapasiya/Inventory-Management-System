package com.kapasiya.ims.inventorymanagementsystem.dto.response;

import com.kapasiya.ims.inventorymanagementsystem.entities.model.Category;
import com.kapasiya.ims.inventorymanagementsystem.entities.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String productName;
    private String description;
    private Double price;
    private int stockLevel;

    @Field(type = FieldType.Object)
    private Category category;

    @Field(type = FieldType.Object)
    private Set<Supplier> suppliers;
}
