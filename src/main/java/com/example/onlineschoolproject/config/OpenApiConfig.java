package com.example.onlineschoolproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI onlineSchoolProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Онлайн Школа для подготовки к ЕГЭ")
                        .description("Сервис, позволяющий подготовиться к ЕГЭ с помощью интернета")
                        .version("0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Kirill Kokorev").email("kirillkokorev22@mail.ru").url(""))
                );
    }
}