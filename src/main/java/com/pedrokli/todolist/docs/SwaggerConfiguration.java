package com.pedrokli.todolist.docs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    private OpenAPI ListToDo(){
        return new OpenAPI()
                .info(new Info()
                        .title("To do list")
                .description("Aplicacao gerenciadora de tarefas")
                .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
