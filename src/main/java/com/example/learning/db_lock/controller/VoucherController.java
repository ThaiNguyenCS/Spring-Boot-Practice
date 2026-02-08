package com.example.learning.db_lock.controller;

import com.example.learning.db_lock.service.VoucherService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
public class VoucherController {
    private final VoucherService voucherService;
    @PostMapping("")
    public ResponseEntity<Boolean> claimVoucher(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(voucherService.claimVoucher(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
