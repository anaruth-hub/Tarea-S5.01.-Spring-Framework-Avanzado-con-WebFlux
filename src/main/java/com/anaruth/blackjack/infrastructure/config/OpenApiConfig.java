package com.anaruth.blackjack.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI blackjackOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blackjack API")
                        .description("Reactive Blackjack API built with Spring Boot WebFlux and Hexagonal Architecture")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Ana Ruth")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project repository")
                        .url("https://github.com/anaruth-hub/Tarea-S5.01.-Spring-Framework-Avanzado-con-WebFlux"));
    }
}