package com.example.learning.multiple_beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class DemoService {
    private final RestTemplate authRestTemplate;
    private final RestTemplate paymentRestTemplate;

    public void callAuthService() {
        authRestTemplate.getForObject("http://example.com/auth", String.class);
    }

    public void callPaymentService() {
        paymentRestTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", String.class);
        paymentRestTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
    }
}
