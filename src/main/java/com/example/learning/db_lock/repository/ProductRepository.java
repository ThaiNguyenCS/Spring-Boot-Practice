package com.example.learning.db_lock.repository;

import com.example.learning.db_lock.dto.ProductDTO;
import com.example.learning.db_lock.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<ProductDTO> findProductById (Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p from Product p where p.id in :ids")
    List<Product> findProductByIdsForOrdering(List<Long> ids);

    List<ProductDTO> findProductByIdIn(List<Long> ids);
}
