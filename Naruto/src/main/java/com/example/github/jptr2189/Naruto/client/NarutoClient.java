package com.example.github.jptr2189.Naruto.client;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.example.github.jptr2189.Naruto.response.Personal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Configura a classe Client
@Service
@Slf4j
@RequiredArgsConstructor

public class NarutoClient {

    // Cria a lista para salvar os personagens pelos metódos "postPersonagemById" e "postPersonagemByName"

    @Getter
    private final List<PersonagemResponse> personagensSalvos = new ArrayList<>();


    // Cria uma instância do WebClient para realizar operações WEB

    private final WebClient webClient;


    // Cria uma instância do "ObjectMapper" para dessiarilizar o JSON

    ObjectMapper objectMapper = new ObjectMapper();


    // Define o caminho padrão da API para ser utilizado nas operações envolvendo ela

    @Autowired

    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api").build();
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

            personagensSalvos.addAll(personagem.collectList().block());
            return getPersonagensSalvos();


    }

    // Cria um novo personagem e salve ele em uma lista (POST fake)

    public List<PersonagemResponse> postNewPersonagem(String nome, String id, String sexo, String idade,
                                                      ArrayList<String> jutsu, ArrayList<String> TipoNatural, ArrayList<String> Ferramentas) {


        log.info("Salvando personagem novo");

        PersonagemResponse personagem = new PersonagemResponse();
        Personal personal = new Personal();
        personagem.setPersonal(personal);


        personagem.setId(id);
        personagem.setName(nome);
        personal.setSex(sexo);
        personal.setAge(idade);
        personagem.setJutsu(jutsu);
        personagem.setNatureType(TipoNatural);
        personagem.setTools(Ferramentas);

        personagensSalvos.add(personagem);
        return getPersonagensSalvos();
    }



    // Remove o personagem com o 'ID' especificado da lista "personagensSalvos"

    public List<PersonagemResponse> deletePersonagemById(String id){

        log.info("Deletando personagem com o id [{}]", id);

                Iterator<PersonagemResponse> iterator = personagensSalvos.iterator();
                while (iterator.hasNext()){

                    PersonagemResponse personagem = iterator.next();
                    String idUsuario = personagem.getId();

                    if(idUsuario.equals(id))

                            iterator.remove();
                }

                return getPersonagensSalvos();

    }


    // Remove o personagem com o 'nome' especificado da lista 'personagensSalvos'

    public List<PersonagemResponse> deletePersonagemByName(String name){

        log.info("Deletando personagem com o nome [{}]", name);

        Iterator<PersonagemResponse> iterator = personagensSalvos.iterator();
        while (iterator.hasNext()){

            PersonagemResponse personagem = iterator.next();
            String idUsuario = personagem.getName();

            if(idUsuario.equals(name))

                iterator.remove();
        }

        return getPersonagensSalvos();

    }


    // Limpa a lista "personagensSalvos"

    public List<PersonagemResponse> cleanDelete(){

        log.info("Limpando a lista");


        personagensSalvos.removeAll(personagensSalvos);

        return getPersonagensSalvos();

    }
}