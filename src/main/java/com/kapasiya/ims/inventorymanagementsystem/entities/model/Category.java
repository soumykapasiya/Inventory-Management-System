package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "category")
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    private String categoryName;
    private String description;
}
