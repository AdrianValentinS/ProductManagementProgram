package com.sample.group.springproductmanagementproject.converter;


import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {

        final ProductDto dto = ProductDto.builder()
                .productID(product.getProductID())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPricePerUnit())
                .productCategory(product.getProductCategory())
                .productQuantity(product.getProductQuantity())
                .build();

        if (dto.getProductID() != null) {
            dto.setProductID(product.getProductID());

        }

        return dto;

    }

    public List<ProductDto> entityToDto(List<Product> product) {
        return product.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Product dtoToEntity(ProductDto dto) {

        Product product = Product.builder()
                .productID((dto.getProductID()))
                .productDescription((dto.getProductDescription()))
                .productPricePerUnit(dto.getProductPrice())
                .productCategory(dto.getProductCategory())
                .productQuantity(dto.getProductQuantity())
                .build();

        if (product.getProductID() != null) {
            product.setProductID(product.getProductID());

        }

        return product;

    }

    public List<Product> dtoToEntity(List<ProductDto> dto) {
        return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}

