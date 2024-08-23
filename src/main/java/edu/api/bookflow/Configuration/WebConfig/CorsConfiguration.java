package edu.api.bookflow.Configuration.WebConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("https://bookflow-phi.vercel.app")
        .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT", "PUT");
    }
}