package com.example.learning.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        try {
            MDC.put("path", request.getRequestURI());
            MDC.put("method", request.getMethod());
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            MDC.put("status", String.valueOf(response.getStatus()));
            MDC.put("durationMs", String.valueOf(duration));
            log.info("Handled request");
            MDC.clear();
        }
    }
}
