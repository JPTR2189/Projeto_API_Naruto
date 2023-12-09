package com.example.github.jptr2189.Naruto.client;

import com.example.github.jptr2189.Naruto.exceptions.PersonagemExisteException;
import com.example.github.jptr2189.Naruto.exceptions.PersonagemNaoEncontradoException;
import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.example.github.jptr2189.Naruto.response.Personal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Configura a classe Client
@Component
@Service
@Slf4j
@AllArgsConstructor
public class NarutoClient {

    // Cria a lista para salvar os personagens pelos metódos "postPersonagemById" , "postPersonagemByName" e "postNewPersonagem"

    @Getter
    private final List<PersonagemResponse> personagensSalvos = new ArrayList<>();


    // Cria uma instância do WebClient para realizar operações WEB

    private WebClient webClient;


    // Cria uma instância do "ObjectMapper" para dessiarilizar o JSON

    ObjectMapper objectMapper = new ObjectMapper();


    // Define o caminho padrão da API para ser utilizado nas operações envolvendo ela

    @Autowired

    public NarutoClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://narutodb.xyz/api").build();
    }


    // Da um GET na API externa e retorna as informações do personagem pelo 'ID'

    public Mono<PersonagemResponse> getPersonagemById(int id) {

        WebClient.Builder builder = WebClient.builder();
        webClient = builder.baseUrl("https://narutodb.xyz/api").build();

        log.info("Buscando o personagem com o id [{}]", id);


            return webClient
                    .get()
                    .uri("/character/{id}", id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {

                        if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {

                            return Mono.error(new PersonagemNaoEncontradoException("Personagem com o id [" + id + "] não encontrado"));

                        }
                        else {

                            return Mono.error(new RuntimeException("Verifique os parâmetros informados"));

                        }
                    })
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
                .onStatus(HttpStatusCode::is4xxClientError, response -> {

                    if (response.statusCode() == HttpStatus.NOT_FOUND) {

                        return Mono.error(new PersonagemNaoEncontradoException("Personagem com o nome [" + name + "] não encontrado"));

                    } else {

                        return Mono.error(new RuntimeException("Verifique os parâmetros informados"));

                    }

                })
                .bodyToMono(String.class)
                .map(this::converteJson);
    }


    // Da um GET na API externa e retorna as informações dos personagens especifícados pelos parâmetros "page" e "size"

    public Mono<String> getAllPersonagens(int page, int size) {

        WebClient.Builder builder = WebClient.builder();
        webClient = builder.baseUrl("https://narutodb.xyz/api").build();

        String parametros = "?page=" + page + "&limit=" + size;

        log.info("Buscando [{}] personagens da página [{}] ", size, page);

        return webClient
                .get()
                .uri("/character" + parametros)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {

                    if (response.statusCode() == HttpStatus.NOT_FOUND) {

                        return Mono.error(new PersonagemNaoEncontradoException("Parâmetros incorretos"));

                    } else {

                        return Mono.error(new RuntimeException("Verifique os parâmetros informados"));
                    }

                })
                .bodyToMono(String.class);

    }


    // Da um GET na Lista "personagensSalvos" e retorna as informações do personagem pelo 'ID'

    public PersonagemResponse getPersonagemFromListById(int id) {

        log.info("Buscando o personagem com o id [{}] na lista", id);

        PersonagemResponse personagemErro = new PersonagemResponse();

        for (PersonagemResponse personagem : personagensSalvos) {

            int idAtual = personagem.getId();


            if (idAtual == id) {

                return personagem;
            }
        }

        return personagemErro;

    }


    // Da um GET na Lista "personagensSalvos" e retorna as informações do personagem pelo 'nome'

    public PersonagemResponse getPersonagemFromListByName(String nome) {

        log.info("Buscando o personagem com o nome [{}] na lista", nome);

        PersonagemResponse personagemErro = new PersonagemResponse();

        for (PersonagemResponse personagem : personagensSalvos) {

            String nomeAtual = personagem.getName();


            if (nomeAtual.equals(nome)) {

                return personagem;
            }

        }

        return personagemErro;

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

    public List<PersonagemResponse> postPersonagemByName(String name) {

        log.info("Salvando personagem com o nome [{}]", name);

        Flux<PersonagemResponse> personagem = webClient
                .get()
                .uri("/character/search?name=" + name)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {

                    if (response.statusCode() == HttpStatus.NOT_FOUND) {

                        return Mono.error(new PersonagemNaoEncontradoException("Personagem com o nome [" + name + "] não encontrado"));

                    } else {

                        return Mono.error(new RuntimeException("Verifique os parâmetros informados"));

                    }

                })
                .bodyToFlux(String.class)
                .map(this::converteJson);


        boolean personagemExiste = false;

        for(PersonagemResponse personagemAtual: personagensSalvos){

            if(personagemAtual.getName().equals(name))
                personagemExiste = true;

        }

        if(personagemExiste)
        {
            throw new PersonagemExisteException("O Personagem ["+ name +"] já existe na lista");

        } else {

            personagensSalvos.addAll(personagem.collectList().block());

            return getPersonagensSalvos();
        }
    }


    // Faz um GET de um personagem pelo 'ID' na API e salva o resultado em uma lista (POST fake)

    public List<PersonagemResponse> postPersonagemById(int id) {

        log.info("Salvando personagem com o id [{}]", id);

        Flux<PersonagemResponse> personagem = webClient

                .get()
                .uri("/character/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()

                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {

                        return Mono.error(new PersonagemNaoEncontradoException("Personagem com o nome [" + id + "] não encontrado"));

                    } else {

                        return Mono.error(new RuntimeException("Verifique os parâmetros informados"));

                    }
                })

                .bodyToFlux(PersonagemResponse.class);


        boolean personagemExiste = false;

        for(PersonagemResponse personagemAtual: personagensSalvos){

            if(personagemAtual.getId() == id)

                personagemExiste = true;

        }

        if(personagemExiste)
        {
            throw new PersonagemExisteException("O Personagem ["+ id +"] já existe na lista");

        } else {

            personagensSalvos.addAll(personagem.collectList().block());

            return getPersonagensSalvos();
        }


    }


    // Cria um novo personagem e salve ele em uma lista (POST fake)

    public List<PersonagemResponse> postNewPersonagem(String nome, int id, String sexo, int idade, String clan,
                                                      ArrayList<String> jutsu, ArrayList<String> tipoNatural, ArrayList<String> ferramentas) {

        log.info("Salvando personagem novo");

        PersonagemResponse personagem = new PersonagemResponse();
        Personal personal = new Personal();
        personagem.setPersonal(personal);

        personagem.setName(nome);
        personagem.setId(id);

        boolean personagemExisteId = false;
        boolean personagemExisteNome = false;


        for(PersonagemResponse personagemAtual: personagensSalvos){

            if(personagemAtual.getId() == id || id < 1430) {

                personagemExisteId = true;
                break;
            }


           else if(personagemAtual.getName().equals(nome)) {

                personagemExisteNome = true;
                break;
           }
        }

        if(personagemExisteId) {

            throw new PersonagemExisteException("Já existe um personagem com o ID [" + id + "]");


        }
        else if(personagemExisteNome) {

            throw new PersonagemExisteException("Já existe um personagem com o Nome [" + nome + "] na lista");


        } else



            personal.setSex(sexo);
            personal.setClan(clan);
            personal.setAge(idade);
            personagem.setJutsu(jutsu);
            personagem.setNatureType(tipoNatural);
            personagem.setTools(ferramentas);


            personagensSalvos.add(personagem);

            return getPersonagensSalvos();

    }


    // Remove o personagem com o 'ID' especificado da lista "personagensSalvos"

    public List<PersonagemResponse> deletePersonagemById(int id) {

        log.info("Deletando personagem com o id [{}]", id);

        Iterator<PersonagemResponse> iterator = personagensSalvos.iterator();

        while (iterator.hasNext()) {

            PersonagemResponse personagem = iterator.next();

            int idUsuario = personagem.getId();

            if (idUsuario == id)

                iterator.remove();
        }

        return getPersonagensSalvos();

    }


    // Remove o personagem com o 'nome' especificado da lista 'personagensSalvos'

    public List<PersonagemResponse> deletePersonagemByName(String nome) {

        log.info("Deletando personagem com o nome [{}]", nome);

        Iterator<PersonagemResponse> iterator = personagensSalvos.iterator();

        while (iterator.hasNext()) {

            PersonagemResponse personagem = iterator.next();
            String nomeUsuario = personagem.getName();

            if (nomeUsuario.equals(nome))

                iterator.remove();
        }

        return getPersonagensSalvos();

    }


    // Limpa a lista "personagensSalvos"

    public List<PersonagemResponse> cleanDelete() {

        log.info("Limpando a lista");

        personagensSalvos.removeAll(personagensSalvos);

        return getPersonagensSalvos();

    }


    // Edita um personagem especifícado pelo "ID" na lista 'personagensSalvos'

    public List<PersonagemResponse> putPersonagemById(int id, String nome, String sexo, int idade, String clan,
                                                      ArrayList<String> jutsu, ArrayList<String> TipoNatural, ArrayList<String> Ferramentas) {

        log.info("Deletando personagem com o id [{}]", id);

        boolean personagemEncontrado = false;

        for (PersonagemResponse personagemAtual : personagensSalvos) {

                if (personagemAtual.getId() == id) {

                    personagemAtual.setId(id);
                    personagemAtual.setName(nome);

                    Personal personal = personagemAtual.getPersonal();

                    personal.setSex(sexo);
                    personal.setAge(idade);
                    personal.setClan(clan);
                    personagemAtual.setJutsu(jutsu);
                    personagemAtual.setNatureType(TipoNatural);
                    personagemAtual.setTools(Ferramentas);

                    personagemEncontrado = true;
                    break;
                }
        }

        if (!personagemEncontrado) {
            throw new PersonagemNaoEncontradoException("Personagem com o ID [" + id + "] não encontrado");
        }

        return getPersonagensSalvos();
    }


    // Edita um personagem especifícado pelo "nome" na lista 'personagensSalvos'

    public List<PersonagemResponse> putPersonagemByName(String nome, int id, String sexo, int idade, String clan,
                                                      ArrayList<String> jutsu, ArrayList<String> TipoNatural, ArrayList<String> Ferramentas) {

        log.info("Deletando personagem com o nome [{}]", nome);

        boolean personagemEncontrado = false;

        for (PersonagemResponse personagemAtual : personagensSalvos) {

            if (personagemAtual.getName().equals(nome)) {

                personagemAtual.setName(nome);

                if (id > 1429) {
                    personagemAtual.setId(id);
                }
                else {
                    
                    throw new PersonagemNaoEncontradoException("Não foi possível editar o personagem, pois já existe um personagem com o ID [" + id + "] ");
                }

                Personal personal = personagemAtual.getPersonal();

                personal.setSex(sexo);
                personal.setAge(idade);
                personal.setClan(clan);
                personagemAtual.setJutsu(jutsu);
                personagemAtual.setNatureType(TipoNatural);
                personagemAtual.setTools(Ferramentas);

                personagemEncontrado = true;
                break;
            }
        }

        if (!personagemEncontrado) {
            throw new PersonagemNaoEncontradoException("Personagem com o nome [" + nome + "] não encontrado");
        }

        return getPersonagensSalvos();
    }
}