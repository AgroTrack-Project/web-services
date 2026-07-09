package org.example.agrotrack.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${documentation.application.description:AgroTrack - Plataforma de monitoreo agricola}")
    String applicationDescription;

    @Value("${documentation.application.version:1.0.0}")
    String applicationVersion;

    @Value("${server.servlet.context-path:}")
    String contextPath;

    @Bean
    public OpenAPI openAPI() {
        final String localServerUrl = "http://localhost:8080%s".formatted(normalizeContextPath(contextPath));
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .description(applicationDescription)
                        .version(applicationVersion)
                        .contact(new Contact()
                                .name("Andes Smart")
                                .email("contacto@andessmart.pe")
                                .url("https://andessmart.pe"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/license/mit/")))
                .externalDocs(new ExternalDocumentation()
                        .description("AgroTrack API Documentation")
                        .url("https://andessmart.pe/docs"))
                .servers(List.of(
                        new Server()
                                .url(localServerUrl)
                                .description("Local Development Environment"),
                        new Server()
                                .url("https://api.agrotrack.andessmart.pe")
                                .description("Production Environment")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    private static String normalizeContextPath(String path) {
        if (path == null || path.isBlank() || "/".equals(path)) {
            return "";
        }
        return path.startsWith("/") ? path : "/%s".formatted(path);
    }
}
