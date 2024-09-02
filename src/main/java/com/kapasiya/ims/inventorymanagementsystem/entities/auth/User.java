package com.kapasiya.ims.inventorymanagementsystem.entities.auth;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String userName;
    private String email;
    private String password;
    private String phone;

    @DBRef
    private Set<Role> roles;
}
