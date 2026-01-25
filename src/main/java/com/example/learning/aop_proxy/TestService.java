package com.example.learning.aop_proxy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final JDKDynamicProxyInterface proxyInterface;

    public String test() {
        return proxyInterface.test();
    }

    @PostConstruct
    public void inspect() {
        System.out.println("here");
        System.out.println(proxyInterface.getClass());
    }
}
