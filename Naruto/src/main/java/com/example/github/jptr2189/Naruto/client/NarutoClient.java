package com.example.github.jptr2189.Naruto.client;
import com.example.github.jptr2189.Naruto.NarutoApplication;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.example.github.jptr2189.Naruto.response.PersonalPersonagemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Configurando o Service
@Service
@Slf4j
public class NarutoClient {

    private final WebClient webClient;


    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api/").build();
    }

// Da um GET na API externa e retorna as informações do personagem pelo ID
    public Mono<String> getPersonagemById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(String.class);


    }

    public Mono<String> getPersonagemByName(String name){

        log.info("Buscando personagem com o nome [{}]", name);
        return webClient
                .get()
                .uri("/character/search?name=" + name)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                    error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(String.class);


    }

}