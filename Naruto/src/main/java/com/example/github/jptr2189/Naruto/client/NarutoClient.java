package com.example.github.jptr2189.Naruto.client;

import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Configurando o Service
@Service
@Slf4j

public class NarutoClient {
    @Getter
    private final List<Mono<PersonagemResponse>> personagensSalvos = new ArrayList<>();


    // Criando uma instância do WebClient
    private final WebClient webClient;

    // Criando uma instância do "ObjectMapper" para dessiarilizar o JSON
    ObjectMapper objectMapper = new ObjectMapper();

    // Define o caminho padrão da API
    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api/").build();
    }

    // Da um GET na API externa e retorna as informações do personagem pelo ID
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

    // Da um GET na API externa e retorna as informações do personagem pelo nome
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

    //TODO ... implementar o metódo postPersonagemByName
    public Mono<PersonagemResponse> postPersonagemByName(String name){

        log.info("Salvando personagem com o nome [{}]", name);

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


    public List<Mono<PersonagemResponse>> postPersonagemById (String id){

        log.info("Salvando personagem com o id []");

         Mono<PersonagemResponse> monoPersonagem = webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(String.class)
                .map(this::converteJson);

            personagensSalvos.add(monoPersonagem);
            return getPersonagensSalvos();


    }

}