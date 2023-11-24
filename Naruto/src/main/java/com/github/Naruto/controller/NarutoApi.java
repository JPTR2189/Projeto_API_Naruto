package com.github.Naruto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// Criando um endpoint
@RestController
@RequestMapping("naruto")
public class NarutoApi {

    @GetMapping("{nome}")
    public PersonagemDTO getPersonagem(@PathVariable String nome) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonagemDTO> resposta = restTemplate.getForEntity(String.format("https://narutodb.xyz/api/character/search?name=%s", nome), PersonagemDTO.class) ;



        return resposta.getBody();

    }
}

