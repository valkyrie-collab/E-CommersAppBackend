package com.valkyrie.api.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AuthenticationByPass {
    ServletRequestAttributes attributes;
    HttpServletRequest request;
    String authHeader;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return restTemplate -> {
            attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                request = attributes.getRequest();
                authHeader = request.getHeader("Authorization");

                if (authHeader != null) {
                    restTemplate.header("Authorization", authHeader);
                }

            }

        };
    }
}
