package com.kapasiya.ims.inventorymanagementsystem.entities.auth;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private String name;
    private String description;
}
