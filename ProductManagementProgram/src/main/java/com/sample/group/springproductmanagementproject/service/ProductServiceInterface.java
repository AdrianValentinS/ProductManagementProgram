package com.sample.group.springproductmanagementproject.service;

import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductServiceInterface {

    @Transactional(rollbackFor = Exception.class)
    Long createProduct(@Valid @NotNull ProductDto dto);

    @Transactional(readOnly = true)
    List<ProductDto> displayProducts();

    @Transactional(readOnly = true)
    List<ProductDto> searchByName(@NotBlank String productName);

    @Transactional(readOnly = true)
    List<ProductDto> searchByID(@NotBlank Long productID);

    @Transactional(rollbackFor = Exception.class)
    void updateProduct(@NotNull Long productID, @NotNull @Valid ProductDto dto);

    @Transactional(rollbackFor = Exception.class)
    void deleteProduct(@NotNull Long productID);

}
