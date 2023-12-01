package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

// Configura a classe Controller
@RestController
@AllArgsConstructor

// Define o endpoint na URL para utilizar a API

@RequestMapping(value = "/naruto", produces = "application/json")

// Adiciona uma tag de indentificação no Swagger

@Tag(name ="narutodb-api")

public class NarutoController {

    NarutoClient narutoClient;

    // Documenta a funcionalidade "getPersonagemById" no Swagger

    @Operation(summary = "Realiza a busca de dados de um personagem espicíficado pelo 'ID'", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })

    // Define o endpoint na URL para utilizar a funcionalidade "getPersonagemById"

    @GetMapping(value = "/id={id}", produces = "application/json")

    public Mono<PersonagemResponse> getPersonagemById(@RequestParam(value = "id") String id) {

        return narutoClient.getPersonagemById(id);

    }

    // Documenta a funcionalidade "getPersonagemByName" no Swagger
    @Operation(summary = "Realiza a busca de dados de um personagem específicado pelo 'nome'", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })

    // Define o endpoint na URL para utilizar a função "getPersonagemByName"

    @GetMapping(value = "/name={name}", produces = "application/json")

    public Mono<PersonagemResponse> getPersonagemByName(@RequestParam(value = "name") String name) {

        return narutoClient.getPersonagemByName(name);

    }

    // Documenta a funcionalidade "postPersonagemByName" no Swagger

    @Operation(summary = "Salva os dados do personagem com o 'nome' específicado", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })

    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemByName"


    @PostMapping("/post/name={name}")

    public List<PersonagemResponse> postPersonagemByName(@RequestBody PersonagemResponse personagem ) {

        return narutoClient.getPersonagensSalvos();

    }

    // Documenta a funcionalidade "postPersonagemById" no Swagger
    @Operation(summary = "Salva os dados do personagem com o 'ID' específicado na lista 'personagensSalvos'", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })

    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemById"

    @PostMapping("/post/id={id}")

    public List<PersonagemResponse> postPersonagemById(@RequestBody PersonagemResponse personagem, @RequestParam String id) {

        return narutoClient.postPersonagemById(id);

    }

}