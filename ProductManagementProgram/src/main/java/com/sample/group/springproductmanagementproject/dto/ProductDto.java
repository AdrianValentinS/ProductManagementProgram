package com.sample.group.springproductmanagementproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull
    private Long productID;

    @NotBlank
    private String productName;

    private String productDescription;

    @NotNull
    private BigDecimal productPrice;


    private String productCategory;

    private Long productQuantity;

}
