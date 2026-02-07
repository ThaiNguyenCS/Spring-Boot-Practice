package com.example.learning.db_lock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product
{
    @Id
    Long id;
    Integer stock;
    String name;
    Double price;
}
