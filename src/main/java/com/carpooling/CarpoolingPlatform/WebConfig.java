package com.carpooling.CarpoolingPlatform;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Autorise les requêtes depuis ton frontend (ex. http://localhost:4200)
        registry.addMapping("/**")  // Applique la configuration CORS à toutes les URL de l'API
                .allowedOrigins("http://localhost:4200")  // Remplace par l'URL de ton frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
