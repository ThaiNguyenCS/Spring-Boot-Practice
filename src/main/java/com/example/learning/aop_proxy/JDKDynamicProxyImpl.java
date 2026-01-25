package com.example.learning.aop_proxy;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JDKDynamicProxyImpl implements JDKDynamicProxyInterface{
//    @Cacheable(cacheNames = "testName")
    @Override
    public String test() {
        this.testNoEffectCache();
        return "test";
    }

    @Cacheable(cacheNames = "testNoEffectCache")
    public String testNoEffectCache() {
        log.info("No Cache. testNoEffectCache called");
        return "testNoEffectCache";
    }
}
