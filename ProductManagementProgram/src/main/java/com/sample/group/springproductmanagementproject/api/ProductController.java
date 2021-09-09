package com.sample.group.springproductmanagementproject.api;

import com.sample.group.springproductmanagementproject.converter.ProductConverter;
import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.dto.ProductDto;
import com.sample.group.springproductmanagementproject.service.ProductServiceImplementation;
import com.sample.group.springproductmanagementproject.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/main")
public class ProductController {

    private final ProductServiceInterface productServiceInterface;

    @Autowired
    public ProductController(ProductServiceInterface productServiceInterface) {
        this.productServiceInterface = productServiceInterface;
    }

    @GetMapping("/search/display")
    public List<ProductDto> displayProducts() {
        return productServiceInterface.displayProducts();
    }

    @GetMapping(value = "/search/id/{productID}")
    public List<ProductDto> displayObjectByID(@PathVariable Long productID) {
        return productServiceInterface.searchByID(productID);
    }

    @GetMapping(value = "/search/name/{productName}")
    public List<ProductDto> displayObjectByName(@PathVariable String productName) {
        return productServiceInterface.searchByName(productName);
    }

    @PostMapping("/add")
    public Long createProduct(@RequestBody ProductDto dto) {
        return productServiceInterface.createProduct(dto);
    }

    @DeleteMapping(path = "/Delete/{productID}")
    public void deleteProduct(@PathVariable("productID") Long productID) {
        productServiceInterface.deleteProduct(productID);
    }

    @PutMapping(path = "/Update/{productID}")
    public void updateProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto dto) {
        productServiceInterface.updateProduct(productID, dto);
    }

}
