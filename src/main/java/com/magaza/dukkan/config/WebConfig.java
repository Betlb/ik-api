package com.magaza.dukkan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200",
                        "http://localhost",
                        "http://localhost:8080",
                        "http://10.3.64.33:8080/",
                        "http://10.3.64.33:8080") // İzin verilen origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metodları
                .allowCredentials(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward all unknown paths to index.html
        registry.addViewController("/{spring:[a-zA-Z0-9\\-_]+}")
                .setViewName("forward:/");
        registry.addViewController("/**/{spring:[a-zA-Z0-9\\-_]+}")
                .setViewName("forward:/");
    }


}