package com.kapasiya.ims.inventorymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {

    @Indexed(unique = true)
    @NotBlank(message = "Please ensure that the Name field is completed and not left blank.")
    private String name;

    @NotBlank(message = "Please ensure that the Description field is completed and not left blank.")
    private String description;
}
