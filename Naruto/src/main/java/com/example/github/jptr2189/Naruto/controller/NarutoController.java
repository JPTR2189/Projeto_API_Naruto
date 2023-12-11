package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Busca os dados dos personagens especifícados de acordo com os parâmetros 'página' e 'tamanho' na API", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Requisição efetuada com exito"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao fazer a requisição"),
    })



    // Define o endpoint na URL para utilizar a função "getAllPersonagens"

    @GetMapping(value = "/all", produces = "application/json")

    public Mono<String> getAllPersonagens(@RequestParam int pagina, @RequestParam int tamanho) {

        return narutoClient.getAllPersonagens(pagina, tamanho);

    }



    // Documenta a funcionalidade "getPersonagemFromListById" no Swagger

    @Operation(summary = "Realiza a busca dos dados de um personagem específicado pelo 'ID' na lista 'personagensSalvos'", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem encontrado"),
            @ApiResponse(responseCode = "400", description = "'ID' inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao procurar o personagem na lista"),
    })



    // Define o endpoint para utilizar a função "getPersonagemFromListById"

    @GetMapping(value = "/list/get/id={id}", produces = "application/json")

    public PersonagemResponse getPersonagemFromListById(@RequestParam int id){

        return narutoClient.getPersonagemFromListById(id);

    }



    // Documenta a funcionalidade "getPersonagemFromListByName" no Swagger

    @Operation(summary = "Realiza a busca dos dados de um personagem específicado pelo 'nome' na lista 'personagensSalvos'", method = "GET")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem com o 'nome' espicíficado encontrado"),
            @ApiResponse(responseCode = "400", description = "nome inválido"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca do personagem"),
    })



    // Define o endpoint para utilizar a função "getPersonagemFromListByName"

    @GetMapping(value = "/list/get/nome={nome}", produces = "application/json")

    public PersonagemResponse getPersonagemFromListByName(@RequestParam String nome){

        return narutoClient.getPersonagemFromListByName(nome);

    }


    // Define o endpoint para utilizar a função "getListaPersonagens"

    @GetMapping(value = "/list/all", produces = "application/json")

    public List<PersonagemResponse> getListaPersonagens(){

        return narutoClient.getListaPersonagens();

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

    @PostMapping("/post/new")

    public List<PersonagemResponse> postNewPersonagem(@RequestBody PersonagemResponse personagem, @RequestParam String nome, @RequestParam int id, @RequestParam String sexo, @RequestParam int idade,
                                                  @RequestParam String clan , @RequestParam ArrayList<String> jutsu , @RequestParam ArrayList<String> tipoNatural, @RequestParam ArrayList<String> ferramentas) {

        return narutoClient.postNewPersonagem(nome, id, sexo, idade, clan , jutsu, tipoNatural, ferramentas);

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



    // Documenta a funcionalidade "putPersonagemFromListById" no Swagger

    @Operation(summary = "Edita um personagem especifícado pelo 'ID' na lista ", method = "PUT")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem Editado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao editar o personagem"),
    })



    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "putPersonagemById"

    @PutMapping("/edit/id={id}")
    public List<PersonagemResponse> putPersonagemById(@RequestParam int id, @RequestParam String nome, @RequestParam String sexo, @RequestParam int idade,
                                                      @RequestParam String clan , @RequestParam ArrayList<String> jutsu , @RequestParam ArrayList<String> tipoNatural, @RequestParam ArrayList<String> ferramentas){


        return narutoClient.putPersonagemById(id, nome, sexo, idade, clan , jutsu, tipoNatural, ferramentas);

    }



    // Documenta a funcionalidade "putPersonagemByName" no Swagger

    @Operation(summary = "Edita um personagem especifícado pelo 'nome' na lista ", method = "PUT")

    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Personagem Editado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao editar o personagem"),
    })



    // Configura o acesso do endpoint na URL para utilizar a funcionalidade "putPersonagemByName"

    @PutMapping("/edit/nome={nome}")
    public List<PersonagemResponse> putPersonagemByName(@RequestParam String nome, @RequestParam int id, @RequestParam String sexo, @RequestParam int idade,
                                                      @RequestParam String clan , @RequestParam ArrayList<String> jutsu , @RequestParam ArrayList<String> tipoNatural, @RequestParam ArrayList<String> ferramentas){


        return narutoClient.putPersonagemByName(nome, id, sexo, idade, clan , jutsu, tipoNatural, ferramentas);

    }

}