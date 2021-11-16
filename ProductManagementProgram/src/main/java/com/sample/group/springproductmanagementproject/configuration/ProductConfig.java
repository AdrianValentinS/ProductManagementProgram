package com.sample.group.springproductmanagementproject.configuration;

import com.sample.group.springproductmanagementproject.dto.ProductDto;
import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.domain.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepo productRepo) {
        return args -> {
            BigDecimal bd = new BigDecimal("99.99");
            Product a = new Product(1L, "a", "a", bd, "a", 1L);
            ArrayList<Product> list = new ArrayList<>();
            list.add(0, a);

            productRepo.saveAll(list);

        };
    }
}
