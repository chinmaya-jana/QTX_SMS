package com.devapp.studentms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(
                        new Info().title("Student Management System (SMS)")
                                .description("By CHINMAY")
                )
                .servers(List.of(new Server().url("http://localhost:8080").description("local"),
                        new Server().url("http://localhost:8081").description("live")))
                .tags(Arrays.asList(
                        new Tag().name("Course APIs"),
                        new Tag().name("Subject APIs"),
                        new Tag().name("CourseSubject APIs"),
                        new Tag().name("Student APIs"),
                        new Tag().name("Address APIs"),
                        new Tag().name("Contact APIs")
                ));
    }
}
