package com.sample.group.springproductmanagementproject.api;

import com.sample.group.springproductmanagementproject.domain.Product;
import com.sample.group.springproductmanagementproject.domain.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepo productRepo) {
        return args -> {
            Product a = new Product(1L, "desc", 1, "a", 1L);
            ArrayList<Product> list = new ArrayList<>();
            list.add(0, a);

            productRepo.saveAll(list);

        };
    }
}
