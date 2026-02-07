package com.example.learning.db_lock.service;

import com.example.learning.db_lock.dto.ProductDTO;
import com.example.learning.db_lock.entity.Product;
import com.example.learning.db_lock.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService
{
    private final ProductRepository  productRepository;

    @Transactional
    public void placeOrder(List<Long> productIds) throws InterruptedException {
        List<Product> productList = productRepository.findProductByIdsForOrdering(productIds);
        for (Product product : productList) {
            if (product.getStock() <= 0) {
                throw new RuntimeException("Cannot order");
            }
        }

        Thread.sleep(2000);

        for (Product product : productList) {
            product.setStock(product.getStock() - 1);
        }

        // Không cần save lại vì đã sử dụng @Transactional
    }
}
