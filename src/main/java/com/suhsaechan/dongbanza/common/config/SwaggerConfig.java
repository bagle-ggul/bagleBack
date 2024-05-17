package com.suhsaechan.dongbanza.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "그녀를 살려라",
        summary = "그녀가 사다준 뉴욕 베이글 (그뉴베)",
        description = "2024년 05월 17일 - 해커톤",
        version = "0.1v"),
    servers = {
        @Server(url = "https://api.she-is-newyork-bagle.co.kr", description = "Main Sever"),
        @Server(url = "http://localhost:8080", description = "Local Server")
    }
)
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    SecurityScheme apiKey = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .in(SecurityScheme.In.HEADER)
        .name("Authorization")
        .scheme("bearer")
        .bearerFormat("JWT");

    SecurityRequirement securityRequirement = new SecurityRequirement()
        .addList("Bearer Token");

    return new OpenAPI()
        .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
        .addSecurityItem(securityRequirement)
        .servers(List.of(
            new io.swagger.v3.oas.models.servers.Server()
                .url("http://localhost:8080")
                .description("Local Server"),
            new io.swagger.v3.oas.models.servers.Server()
                .url("https://api.she-is-newyork-bagle.co.kr")
                .description("Main Server")
            )
        );
  }
}