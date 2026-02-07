package com.example.learning.db_lock.controller;

import com.example.learning.db_lock.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public void placeOrder(@RequestBody List<Long> productIds) {
        try {
            orderService.placeOrder(productIds);
        } catch (InterruptedException e)
        {
        }
    }
}
