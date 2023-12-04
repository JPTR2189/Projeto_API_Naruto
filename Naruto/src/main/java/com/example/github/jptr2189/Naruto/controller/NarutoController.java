package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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

    @Operation(summary = "Realiza a busca dos dados de um personagem específicado pelo 'ID' na API", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })



    // Define o endpoint na URL para utilizar a funcionalidade "getPersonagemById"

    @GetMapping(value = "/id={id}", produces = "application/json")

    public Mono<PersonagemResponse> getPersonagemById(@RequestParam(value = "id") int id) {

        return narutoClient.getPersonagemById(id);

    }



    // Documenta a funcionalidade "getPersonagemByName" no Swagger
    @Operation(summary = "Realiza a busca dos dados de um personagem específicado pelo 'nome' na API", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })



    // Define o endpoint na URL para utilizar a função "getPersonagemByName"

    @GetMapping(value = "/nome={nome}", produces = "application/json")

    public Mono<PersonagemResponse> getPersonagemByName(@RequestParam(value = "nome") String nome) {

        return narutoClient.getPersonagemByName(nome);

    }



    // Documenta a funcionalidade getAllPersonagens" no Swagger

    @Operation(summary = "Busca os dados dos 20 primeiros personagens na API", method = "GET")

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



    // Documenta a funcionalidade "getPersonagemFromListById" no Swagger

    @Operation(summary = "Realiza a busca dos dados de um personagem específicado pelo 'ID' na lista 'personagensSalvos'", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })



    // Define o endpoint para utilizar a função "getPersonagemFromListById"

    @GetMapping(value = "/list/get", produces = "application/json")

    public PersonagemResponse getPersonagemFromListById(@RequestParam int id){

        return narutoClient.getPersonagemFromListById(id);

    }



    // Documenta a funcionalidade "postPersonagemByName" no Swagger

    @Operation(summary = "Faz um GET na API e salva os dados do personagem com o 'nome' específicado na lista 'personagensSalvos'", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'nome' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar o personagem"),
    })



    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemByName"


    @PostMapping("/post/nome={nome}")

    public List<PersonagemResponse> postPersonagemByName(@RequestBody PersonagemResponse personagem, @RequestParam String nome) {

        return narutoClient.postPersonagemByName(nome);

    }


    @Operation(summary = "Faz um GET na API e salva os dados do personagem com o 'ID' específicado na lista 'personagensSalvos'", method = "POST")


    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'ID' espicíficado salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "'ID' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar o personagem"),
    })



    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postPersonagemById"

    @PostMapping("/post/id={id}")

    public List<PersonagemResponse> postPersonagemById(@RequestBody PersonagemResponse personagem, @RequestParam int id) {

        return narutoClient.postPersonagemById(id);

    }



    // Documenta a funcionalidade "postNewPersonagem" no Swagger

    @Operation(summary = "Cria um novo personagem e salva na lista 'personagensSalvos'", method = "POST")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem salvo"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos "),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar o personagem"),
    })



    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "postNewPersonagem"

    @PostMapping("/post/new/{nome}/{id}/{sexo}/{idade}/{jutsu}/{TipoNatural}/{Ferramentas}")

    public List<PersonagemResponse> postNewPersonagem(@RequestBody PersonagemResponse personagem, @RequestParam String nome, @RequestParam int id, @RequestParam String sexo, @RequestParam int idade,
                                                      @RequestParam ArrayList<String> jutsu , @RequestParam ArrayList<String> tipoNatural, @RequestParam ArrayList<String> ferramentas) {

        return narutoClient.postNewPersonagem(nome, id, sexo, idade, jutsu, tipoNatural, ferramentas);

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
    public List<PersonagemResponse> deletePersonagemById(@RequestBody PersonagemResponse personagem ,@RequestParam int id){

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

    @DeleteMapping("/delete/nome{nome}")
    public List<PersonagemResponse> deletePersonagemByName(@RequestBody PersonagemResponse personagem, @RequestParam String nome){

        return narutoClient.deletePersonagemByName(nome);

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