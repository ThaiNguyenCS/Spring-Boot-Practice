package com.example.learning.db_lock.dto;

import lombok.Data;

@Data
public class ProductDTO {
    Long id;
    String name;
    Integer stock;
    Float price;
}
