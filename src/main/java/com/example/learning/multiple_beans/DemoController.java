package com.example.learning.multiple_beans;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/multiple-beans")
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/auth")
    public String callAuth() {
        demoService.callAuthService();
        return "Called auth service";
    }

    @GetMapping("/payment")
    public String callPayment() {
        demoService.callPaymentService();
        return "Called payment service";
    }
}
