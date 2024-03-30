package com.example.talent3demoresepmakanan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication

@OpenAPIDefinition(info = @Info(
        title = "TALENT 3 - DEMO RESEP MAKANAN",
        description = "Resep Makanan table Categories, FavoriteFoods, Levels, Recipes, Users to final submission Arutalab is the best",
        version = "1.0"
))
public class Talent3DemoResepMakananApplication implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {


    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }
    public static void main(String[] args) {
        SpringApplication.run(Talent3DemoResepMakananApplication.class, args);
    }


    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(1030);
    }

}
