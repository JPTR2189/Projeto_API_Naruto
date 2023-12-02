package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;
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

    // Chama a "Client" para dentro da "Controller"

    NarutoClient narutoClient;


    // Documenta a funcionalidade "getPersonagemById" no Swagger

    @Operation(summary = "Realiza a busca de dados de um personagem espicíficado pelo 'ID' na API", method = "GET")

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
    @Operation(summary = "Realiza a busca de dados de um personagem específicado pelo 'nome' na API", method = "GET")

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

    // Documenta a funcionalidade getAllPersonagens" no Swagger

    @Operation(summary = "Busca os dados de todos os personagens", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Requisição efetuada com exito"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao fazer a requisição"),
    })


    // Define o endpoint na URL para utilizar a função "getAllPersonagens"

    @GetMapping(value = "/all", produces = "application/json")

    public Mono<String> getAllPersonagens() {

        return narutoClient.getAllPersonagens();

    }


    // Documenta a funcionalidade "postPersonagemByName" no Swagger

    @Operation(summary = "Salva os dados do personagem com o 'nome' específicado na lista 'PersonagensSalvos'", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })


    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemByName"


    @PostMapping("/post/name={name}")

    public List<PersonagemResponse> postPersonagemByName(@RequestBody PersonagemResponse personagem, @RequestParam String name) {

        return narutoClient.postPersonagemByName(name);

    }


    // Documenta a funcionalidade "postPersonagemById" no Swagger
    @Operation(summary = "Salva os dados do personagem com o 'ID' específicado na lista 'personagensSalvos'", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'ID' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })


    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemById"

    @PostMapping("/post/id={id}")

    public List<PersonagemResponse> postPersonagemById(@RequestBody PersonagemResponse personagem, @RequestParam String id) {

        return narutoClient.postPersonagemById(id);

    }


    // Documenta a funcionalidade "deletePersonagemById" no Swagger
    @Operation(summary = "Deleta os dados do personagem com o 'ID' específicado na lista 'personagensSalvos'", method = "DELETE")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado deletado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'ID' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })


    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "deletePersonagemById"

    @DeleteMapping("/delete/id={id}")
    public List<PersonagemResponse> deletePersonagemById(@RequestBody PersonagemResponse personagem ,@RequestParam String id){

        return narutoClient.deletePersonagemById(id);

    }


    // Documenta a funcionalidade "deletePersonagemByName" no Swagger

    @Operation(summary = "Deleta os dados do personagem com o 'nome' específicado na lista 'personagensSalvos'", method = "DELETE")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado deletado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o salvamento do personagem"),
    })


    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "deletePersonagemByName"

    @DeleteMapping("/delete/name{name}")
    public List<PersonagemResponse> deletePersonagemByName(@RequestBody PersonagemResponse personagem, @RequestParam String name){

        return narutoClient.deletePersonagemByName(name);

    }


    // Documenta a funcionalidade "cleanDelete" no Swagger

    @Operation(summary = "Da um clean na lista, limpando todos os personagens existentes nela", method = "DELETE")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Lista limpada com éxito"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao limpar a lista"),
    })


    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "cleanDelete"

    @DeleteMapping("/delete/all")
    public List<PersonagemResponse> cleanDelete(@RequestBody PersonagemResponse personagem){

        return narutoClient.cleanDelete();

    }

}