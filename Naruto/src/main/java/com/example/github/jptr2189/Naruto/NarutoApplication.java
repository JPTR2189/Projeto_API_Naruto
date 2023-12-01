package com.example.github.jptr2189.Naruto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// Inicia a aplicação WEB
@OpenAPIDefinition(info = @Info(title = "Projeto API Naruto", description = "Projeto que faz uma conexão com a API externa do 'narutodb.xyz/api' e recebe informções dos personagens em um formato JSON, que pode ser manipulado depois."))
@SpringBootApplication
@EnableScheduling
public class NarutoApplication {

	public static void main(String[] args) {SpringApplication.run(NarutoApplication.class, args);}
}
