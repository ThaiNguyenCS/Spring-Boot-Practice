package com.example.learning.db_lock.controller;

import com.example.learning.db_lock.dto.ProductDTO;
import com.example.learning.db_lock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct (@PathVariable Long id,
                                                 @RequestBody ProductDTO productDto) {
        return ResponseEntity.ok("Update successfully");
    }

}
