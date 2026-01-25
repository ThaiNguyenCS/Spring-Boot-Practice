package com.example.learning.aop_proxy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop_proxy")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    @PostMapping
    public ResponseEntity<String> test (@RequestBody String id) {
        testService.test();
        return ResponseEntity.ok(id);
    }
}
