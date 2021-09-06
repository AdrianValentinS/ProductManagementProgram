package com.sample.group.springproductmanagementproject.service;

//import com.sample.group.springtutorialproject.domain.ObjectRepo;
import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.domain.ProductRepo;
//import com.sample.group.springtutorialproject.domain.SampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService{

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> displayProducts() {
        return productRepo.findAll();
    }

    public List<Product> findByID(Long productID) {
        return new ArrayList<>(productRepo.findAllByProductID(productID));
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProduct(Long productID) {
        boolean exists = productRepo.existsById(productID);
        if (!exists) {
            throw new IllegalStateException("Object by ID " + productID + " does not exist.");
        }
        productRepo.deleteById(productID);
    }

    @Transactional
    public void updateProduct(Long productID, String productDescription, int productPricePerUnit, String productCategory, Long productQuantity) {
        Product product = productRepo.findById(productID).orElseThrow(() -> new IllegalStateException("Object with ID " + productID + " does not exist."));
        product.setProductID(productID);
        product.setProductDescription(productDescription);
        product.setProductPricePerUnit(productPricePerUnit);
        product.setProductCategory(productCategory);
        product.setProductQuantity(productQuantity);
    }
}
