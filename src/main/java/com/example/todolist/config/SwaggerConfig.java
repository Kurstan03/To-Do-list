package com.example.todolist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        Info appInfo = new Info();
        appInfo.title("To-Do list");
        appInfo.version("1.0");
        appInfo.description("This API exposes endpoints to manage task list.");

        return new OpenAPI()
                .info(appInfo);
    }
}
