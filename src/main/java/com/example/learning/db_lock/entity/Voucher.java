package com.example.learning.db_lock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name="vouchers")
@Data
public class Voucher {
    @Id
    Long id;
    Integer maxQuantity;
    Integer claimCount;
    @Version
    Long version;
}
