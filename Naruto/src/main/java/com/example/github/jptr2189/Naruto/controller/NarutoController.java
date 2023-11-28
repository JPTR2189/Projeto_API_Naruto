package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// Configurando o Controller
@RestController
@AllArgsConstructor

// Define o endpoint de funcionamento da API

@RequestMapping(value = "/naruto", produces = "application/json")
@Tag(name ="narutodb-api")
public class NarutoController {

    NarutoClient narutoClient;

    // Define o endpoint para utilizar a função "getPersonagemById"
    @Operation(summary = "Realiza a busca de dados de um personagem espicíficado pelo 'ID'", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })

    @GetMapping("/id={id}")
    public Mono<String> getPersonagemById(@RequestParam(value = "id") String id) {

        return narutoClient.getPersonagemById(id);

    }

    @Operation(summary = "Realiza a busca de dados de um personagem específicado pelo 'nome'", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })

    @GetMapping("/name={name}")
    public Mono<String> getPersonagemByName(@RequestParam(value = "name") String name){
        return narutoClient.getPersonagemByName(name);

    }
}
