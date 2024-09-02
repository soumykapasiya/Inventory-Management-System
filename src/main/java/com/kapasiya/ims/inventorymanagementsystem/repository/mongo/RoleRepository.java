package com.kapasiya.ims.inventorymanagementsystem.repository.mongo;

import com.kapasiya.ims.inventorymanagementsystem.entities.auth.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);

}
