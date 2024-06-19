package edu.api.bookflow.Configuration;

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
        .allowedOrigins("https://127.0.0.1:3000") //Mudar para "https://bookflow-phi.vercel.app" após finalização do projeto
        .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT", "PUT");
    }
}