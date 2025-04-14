package com.microservice.users.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Token";

        return new OpenAPI()
                .info(
                    new Info().title("Documentación de la API")
                    .description("API documentación del sistema de Jardinería ")
                    .version("1.0")
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .tags(List.of(
                        new Tag().name("API de gestión de autenticación y usuarios").description("Autenticación y usuarios")
                        /*new Tag().name("API de Estados").description("CRUD de los distintos estados de los usuarios"),
                        new Tag().name("API de Tipo de Servicios").description("CRUD de los tipos de servicios del sistema"),
                        new Tag().name("API de Servicios").description("CRUD de los servicios")*/
                ))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))); // Opcional, indica que el token es JWT
    }


}
