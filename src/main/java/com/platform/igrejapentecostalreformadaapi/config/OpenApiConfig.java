package com.platform.igrejapentecostalreformadaapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Igreja Pentecostal Reformada services")
                        .version("v1")
                        .description("Documentation about Igreja Pentecostal Reformada api`s")
                        .termsOfService("https://pub.lspeixotodev.com.br/courses")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://pub.lspeixotodev.com.br/courses")
                        )
                );
    }
}
