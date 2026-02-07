package com.example.learning.db_lock.init;

import com.example.learning.db_lock.entity.Product;
import com.example.learning.db_lock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;

    @Bean
    CommandLineRunner productInit() {
        return args -> {
            productRepository.deleteAll();

            for (long i = 1; i <= 10; i++) {
                Product product = new Product();
                product.setId(i);
                product.setName("Product " + i);
                product.setStock(1); // set là 1 để test lock
                product.setPrice(Math.random()*100000);

                productRepository.save(product);
            }

            System.out.println("Inserted 10 products successfully!");
        };
    }
}
