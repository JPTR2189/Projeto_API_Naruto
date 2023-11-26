package com.example.github.jptr2189.Naruto.client;
import com.example.github.jptr2189.Naruto.NarutoApplication;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class NarutoClient {

    private final WebClient webClient;


    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api/").build();
    }


    public Mono<PersonagemResponse> getPersonagemById(String id) {
        log.info("Buscando o personagem com o id [{}]", id);
        return webClient
                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parâmetros informados")))
                .bodyToMono(PersonagemResponse.class);


    }



}