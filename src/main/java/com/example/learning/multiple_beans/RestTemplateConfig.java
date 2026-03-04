package com.example.learning.multiple_beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {
    @Bean("authRestTemplate")
    public RestTemplate authRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((req, body, ex) -> {
            log.info("Auth rest template is used");
            return ex.execute(req, body);
        });
        return restTemplate;
    }

    @Bean("paymentRestTemplate")
    public RestTemplate paymentRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((req, body, ex) -> {
            log.info("Payment rest template is used");
            return ex.execute(req, body);
        });
        return restTemplate;
    }

    @Bean
    public RestTemplate defaultRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((req, body, ex) -> {
            log.info("Default rest template is used");
            return ex.execute(req, body);
        });
        return restTemplate;
    }
}
