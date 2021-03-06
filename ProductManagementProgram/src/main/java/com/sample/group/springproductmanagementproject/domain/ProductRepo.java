package com.sample.group.springproductmanagementproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByProductID(Long productID);
    List<Product> findAllByProductNameContaining(String productName);
}
