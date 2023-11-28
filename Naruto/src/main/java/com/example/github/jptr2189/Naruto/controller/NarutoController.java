package com.example.github.jptr2189.Naruto.controller;

import com.example.github.jptr2189.Naruto.client.NarutoClient;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// Configurando o Controller
@RestController
@AllArgsConstructor
// Define o endpoint de funcionamento da API
@RequestMapping("/naruto")
public class NarutoController {

    NarutoClient narutoClient;

    // Define o endpoint para utilizar a função "getPersonagemById"
    @GetMapping("/id={id}")
    public Mono<String> getPersonagemById(@PathVariable String id) {

        return narutoClient.getPersonagemById(id);

    }

    @GetMapping("/name={name}")
    public Mono<String> getPersonagemByName(@PathVariable String name){
        return narutoClient.getPersonagemByName(name);

    }
}
