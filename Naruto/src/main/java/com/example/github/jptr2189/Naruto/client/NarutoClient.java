package com.example.github.jptr2189.Naruto.client;

import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Configura a classe Client
@Service
@Slf4j

public class NarutoClient {
    
    // Cria a lista para salvar os personagens pelos metódos "postPersonagemById" e "postPersonagemByName"

    @Getter
    private final List<PersonagemResponse> personagensSalvos = new ArrayList<>();


    // Cria uma instância do WebClient para realizar operações WEB

    private final WebClient webClient;

    // Cria uma instância do "ObjectMapper" para dessiarilizar o JSON

    ObjectMapper objectMapper = new ObjectMapper();

    // Define o caminho padrão da API para ser utilizado nas operações envolvendo ela

    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api/").build();
    }

    // Da um GET na API externa e retorna as informações do personagem pelo 'ID'

    public Mono<PersonagemResponse> getPersonagemById(String id) {

        log.info("Buscando o personagem com o id [{}]", id);

        return webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(String.class)
                .map(this::converteJson);


    }

    // Da um GET na API externa e retorna as informações do personagem pelo 'nome'

    public Mono<PersonagemResponse> getPersonagemByName(String name) {

        log.info("Buscando personagem com o nome [{}]", name);

        return webClient
                .get()
                .uri("/character/search?name=" + name)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(String.class)
                .map(this::converteJson);
    }

    // Converte o Json em um Objeto Java com o "ObjectMapper"

    public PersonagemResponse converteJson(String jsonString) {


        try {

            ObjectMapper objectMapper = new ObjectMapper();
            PersonagemResponse personagemResponse = objectMapper.readValue(jsonString, PersonagemResponse.class);
            return personagemResponse;

        } catch (JsonProcessingException jpe) {

            throw new RuntimeException("Erro ao converter JSON para o objeto PersonagemResponse", jpe);
        }

    }

    // Faz um GET de um personagem pelo 'nome' na API e salva o resultado em uma lista (POST fake)

    public List<PersonagemResponse> postPersonagemByName(String name){

        log.info("Salvando personagem com o nome [{}]", name);

        Flux<PersonagemResponse> personagem = webClient
                .get()
                .uri("/character/search?name=" + name)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToFlux(PersonagemResponse.class);

        personagensSalvos.addAll(personagem.collectList().block());
        return getPersonagensSalvos();

    }

    // Faz um GET de um personagem pelo 'ID' na API e salva o resultado em uma lista (POST fake)

    public List<PersonagemResponse> postPersonagemById (String id){

        log.info("Salvando personagem com o id [{}]", id);

         Flux<PersonagemResponse> personagem = webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToFlux(PersonagemResponse.class);
                //.flatMap(responseMap -> Mono.justOrEmpty(responseMap.get("personagemResponse")))
                 //.cast(PersonagemResponse.class);

            personagensSalvos.addAll(personagem.collectList().block());
            return getPersonagensSalvos();


    }

}