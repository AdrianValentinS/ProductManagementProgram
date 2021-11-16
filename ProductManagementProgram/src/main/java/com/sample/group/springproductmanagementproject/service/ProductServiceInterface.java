package com.sample.group.springproductmanagementproject.service;

import org.springframework.transaction.annotation.Transactional;
import com.sample.group.springproductmanagementproject.dto.ProductDto;
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

    @Transactional(readOnly = true)
    ProductDto searchByIDSingleEntity(@NotBlank Long productID);

    @Transactional(rollbackFor = Exception.class)
    void updateProduct(@NotNull Long productID, @NotNull @Valid ProductDto dto); // from return void to return object, might cause issues

    @Transactional(rollbackFor = Exception.class)
    void deleteProduct(@NotNull Long productID);

}
