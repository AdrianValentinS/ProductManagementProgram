package com.sample.group.springproductmanagementproject.api;

import com.sample.group.springproductmanagementproject.converter.ProductStockConverter;
import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.dto.ProductStockDto;
import com.sample.group.springproductmanagementproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/main")
public class ProductController {

    private final ProductService productService;
    private final ProductStockConverter productStockConverter;


    @Autowired
    public ProductController(ProductService productService, ProductStockConverter productStockConverter) {
        this.productService = productService;
        this.productStockConverter = productStockConverter;
    }


    @GetMapping("/search/display")
    public List<Product> displayProductsController() {
        return productService.displayProducts();
    }

    @GetMapping(value = "/search/{productID}")
    public List<Product> displayObjectBySpecificID(@PathVariable Long productID) {
        return productService.findByID(productID);
    }

    @GetMapping(value = "/searchByDto/display")
    public List<ProductStockDto> displayProductsControllerByDto() {
        List<Product> displayProductsControllerByDto = productService.displayProducts();
        return productStockConverter.entityToDto(displayProductsControllerByDto);
    }

    // Search by DTO broken, cannot parse Product class cast, needs fix.

    @GetMapping(value = "/searchByDto/{productID}")
    public ProductStockDto displayProductsControllerByDtoByID(@PathVariable Long productID) {
        Product product = (Product) productService.findByID(productID);
        return productStockConverter.entityToDto(product);
    }

    @PostMapping("/addViaDto")
    public ProductStockDto insertProductDto(@RequestBody ProductStockDto dto){
        Product product = productStockConverter.dtoToEntity(dto);
        productService.addProduct(product);
        return productStockConverter.entityToDto(product);
    }


    @PostMapping("/add")
    public void insertProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping(path = "/Delete/{productID}")
    public void deleteObject(@PathVariable("productID") Long productID) {
        productService.deleteProduct(productID);
    }

    @PutMapping(path = "/Update/{productID}")
    public void updateProduct(@PathVariable("productID") Long productID,
                              @RequestParam(required = false) String productDescription,
                              @RequestParam(required = false) int productPricePerUnit,
                              @RequestParam(required = false) String productCategory,
                              @RequestParam(required = false) Long productQuantity) {
        productService.updateProduct(productID, productDescription, productPricePerUnit, productCategory, productQuantity);
    }
}
