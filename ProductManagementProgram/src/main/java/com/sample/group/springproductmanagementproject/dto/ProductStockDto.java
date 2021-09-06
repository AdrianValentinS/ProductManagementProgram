package com.sample.group.springproductmanagementproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductStockDto {

    private Long productID;
    private String productDescription;
    private Long productQuantity;

}
