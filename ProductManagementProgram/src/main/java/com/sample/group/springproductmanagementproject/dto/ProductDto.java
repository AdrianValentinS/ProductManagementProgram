package com.sample.group.springproductmanagementproject.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
@Validated
public class ProductDto {

    @NotBlank
    private Long productID;
    @NotBlank
    private String productName;
    private String productDescription;
    @NotBlank
    private BigDecimal productPrice;
    private String productCategory;
    private Long productQuantity;

}
