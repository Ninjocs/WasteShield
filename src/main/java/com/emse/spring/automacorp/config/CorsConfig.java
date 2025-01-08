package com.emse.spring.automacorp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for all paths and all origins (e.g., allow frontend at localhost:8000 or cleverapps.io)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000", "https://wasteshield.cleverapps.io") // Allowed frontend origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials (cookies, authentication headers, etc.)
    }
}

