package com.example.final_exam_chervonenkoekaterina.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// http://localhost:8081/swagger-ui/index.html

//{
//  "email": "chrvn.katya@gmail.com",
//  "password": "123456789"
//}

@Configuration
public class ChervonenkoEkaterinaOpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // описание самого API
                .info(new Info()
                        .title("Course Management System API")
                        .version("1.0.0")
                        .description("Финальный проект по курсу 'Web Component Development'. Студент: Червоненко Екатерина")
                        .contact(new Contact()
                                .name("Ekaterina Chervonenko")
                                .email("katysik.chervonenko@gmail.com")))

                // поддержка JWT авторизации в интерфейс Swagger
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}