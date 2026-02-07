package com.example.learning.db_lock.service;

import com.example.learning.db_lock.dto.ProductDTO;
import com.example.learning.db_lock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductDTO findById(Long id) {
        Optional<ProductDTO> product = productRepository.findProductById(id);
        return product.orElse(null);
    }
}
