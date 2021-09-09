package com.sample.group.springproductmanagementproject.service;

import com.sample.group.springproductmanagementproject.converter.ProductConverter;
import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.domain.ProductRepo;
import com.sample.group.springproductmanagementproject.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductServiceInterface{

    private final ProductRepo productRepo;
    private final ProductConverter productConverter;

    @Autowired
    public ProductServiceImplementation(ProductRepo productRepo, ProductConverter productConverter) {
        this.productRepo = productRepo;
        this.productConverter = productConverter;
    }

    @Override
    public Long createProduct(ProductDto dto) {
        return productRepo.save(Product.builder()
                .productID(dto.getProductID())
                .productName(dto.getProductName())
                .productDescription(dto.getProductDescription())
                .productPricePerUnit(dto.getProductPrice())
                .productCategory(dto.getProductCategory())
                .productQuantity(dto.getProductQuantity())
                .build()
        )
                .getProductID();
    }

    @Override
    public List<ProductDto> displayProducts() {
        return productRepo.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByName(String productName) {
        return productRepo.findAllByProductNameContaining(productName).stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByID(Long productID) {
        return productRepo.findAllByProductID(productID).stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateProduct(Long productID, ProductDto dto) {
        final Product product = searchByIDNested(productID);
        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductPricePerUnit(dto.getProductPrice());
        product.setProductCategory(dto.getProductCategory());
        product.setProductQuantity(dto.getProductQuantity());

        productRepo.save(product);
    }

    private Product searchByIDNested(Long productID){
        return productRepo.findById(productID).orElseThrow(() -> new IllegalStateException("Invalid product ID."));
    }

    @Override
    public void deleteProduct(Long productID) {
        boolean exists = productRepo.existsById(productID);
        if (!exists) {
            throw new IllegalStateException("Object by ID " + productID + " does not exist.");
        }
        productRepo.deleteById(productID);
    }
}
