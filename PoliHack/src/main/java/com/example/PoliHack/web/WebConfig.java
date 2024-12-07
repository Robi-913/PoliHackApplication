package com.example.PoliHack.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite cereri pe toate rutele
                        .allowedOrigins("http://localhost:5173") // Domeniul frontend-ului
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode HTTP permise
                        .allowedHeaders("*") // Toate headerele permise
                        .allowCredentials(true); // Permite cookies/sesiuni
            }
        };
    }
}
