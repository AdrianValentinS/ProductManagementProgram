package com.sample.group.springproductmanagementproject.converter;


import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.dto.ProductStockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductStockConverter {

    public ProductStockDto entityToDto(Product product) {

        final ProductStockDto dto = ProductStockDto.builder()
                .productID(product.getProductID())
                .productDescription(product.getProductDescription())
                .productQuantity(product.getProductQuantity())
                .build();

        if (dto.getProductID() != null) {
            dto.setProductID(product.getProductID());

        }

        return dto;

    }

    public List<ProductStockDto> entityToDto(List<Product> product) {
        return product.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Product dtoToEntity(ProductStockDto dto) {

        Product product = Product.builder()
                .productID((dto.getProductID()))
                .productDescription((dto.getProductDescription()))
                .productQuantity(dto.getProductQuantity())
                .build();

        if (product.getProductID() != null) {
            product.setProductID(product.getProductID());

        }

        return product;

    }

    public List<Product> dtoToEntity(List<ProductStockDto> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}

