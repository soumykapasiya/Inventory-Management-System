package com.kapasiya.ims.inventorymanagementsystem.entities.model;

import com.kapasiya.ims.inventorymanagementsystem.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "order")
public class Order extends BaseEntity {

    private String productId;
    private Integer quantity;
    private Date orderDate;
    private Date deliveryDate;
    private String warehouseId;

    @Builder.Default
    private String orderStatus = "Pending";
}
