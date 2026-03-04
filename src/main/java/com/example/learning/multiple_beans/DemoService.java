package com.example.learning.multiple_beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class DemoService {
    private final RestTemplate authRestTemplate;
    @Qualifier("paymentRestTemplate")
    private final RestTemplate restTemplate;

    public void callAuthService() {
        authRestTemplate.getForObject("http://example.com/auth", String.class);
    }

    public void callPaymentService() {
        restTemplate.getForObject("http://example.com/payment", String.class);
    }
}
