package com.example.github.jptr2189.Naruto.client;

import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import com.example.github.jptr2189.Naruto.response.Personal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class NarutoClientTest {

    @BeforeEach
    public void init() {
        WebClient.Builder builder = WebClient.builder();
        webClient = builder.baseUrl("https://narutodb.xyz/api").build();

    }

    @InjectMocks
    public NarutoClient client;

    @Mock
    private WebClient webClient;

    @Mock
    PersonagemResponse personagemResponse;

     List<PersonagemResponse> personagensSalvos = new ArrayList<>();


    // Teste unitário da função "getPersonagemById"

    @Test
    void deveBuscarPersonagemPeloIdNaAPI() {

        PersonagemResponse personagem = PersonagemResponse.builder().id(55).build();

        when(client.getPersonagemById(personagem.id)).thenReturn(personagem);

        Mono<PersonagemResponse> retorno = client.getPersonagemById(personagem.id);

        PersonagemResponse retornoSemMono = retorno.block();

        assertEquals(retornoSemMono.getId(), personagem.getId());
        Assertions.assertThat(retorno).isNotNull();


    }

    // Teste unitário da função "getPersonagemByName"

    @Test
    void deveBuscarPersonagemPeloNomeNaAPi() {

        PersonagemResponse personagem = PersonagemResponse.builder().name("Amachi").build();

        when(client.getPersonagemByName(personagem.name)).thenReturn(personagem);

        Mono<PersonagemResponse> retorno = client.getPersonagemByName(personagem.name);

        PersonagemResponse retornoSemMono = retorno.block();

        assertEquals(retornoSemMono.getName(), personagem.getName());
        Assertions.assertThat(retorno).isNotNull();


    }

// Teste unitário da função "getAllPersonagens"

    @Test
    void deveBuscarPersonagensPorPaginacaoNaAPi(){

        int page = 3;
        int size = 1;
        String id = null;

        PersonagemResponse personagem = PersonagemResponse.builder().name("Three-Headed Guardian Beast").id(2).build();


        Mono<String> retorno = client.getAllPersonagens(page, size);

        String retornoSemMono = retorno.block();

        when(client.getAllPersonagens(page, size)).thenReturn(personagem);


        Pattern patternId = Pattern.compile("\"id\":(\\d+)");
        Matcher matcherId = patternId.matcher(retornoSemMono);

        if (matcherId.find()) {
            String idValue = matcherId.group(1);

            assertEquals(idValue, String.valueOf(personagem.getId()));


        } else {
            System.out.println("Nenhum valor de id encontrado no JSON.");
        }

        Pattern patternName = Pattern.compile("\"name\":\"([^\"]+)\"");
        Matcher matcherName = patternName.matcher(retornoSemMono);

        if (matcherName.find()) {
            String nameValue = matcherName.group(1);

            assertEquals(nameValue, (personagem.getName()));


        } else {
            System.out.println("Nenhum valor de name encontrado no JSON.");
        }

    }

    // Teste unitário da função "getPersonagemFromListById"

    @Test
    void deveBuscarPersonagemNaListaPeloId(){

        PersonagemResponse personagem = PersonagemResponse.builder().id(55).build();

        client.postPersonagemById(personagem.id);

        PersonagemResponse personagemLista = client.getPersonagemFromListById(personagem.id);

        when(client.getPersonagemById(personagem.getId())).thenReturn(personagem);

        assertEquals(personagemLista.getId(), personagem.getId());



    }


    // Teste unitário da função "getPersonagemFromListByName"

    @Test
    void deveBuscarPersonagemNaListaPeloNome(){

       PersonagemResponse personagem = PersonagemResponse.builder().name("Amachi").build();

        client.postPersonagemByName(personagem.name);

        PersonagemResponse personagemLista = client.getPersonagemFromListByName(personagem.name);

        when(client.getPersonagemByName(personagem.getName())).thenReturn(personagem);

        assertEquals(personagemLista.getName(), personagem.getName());

    }

    // Teste unitário da função "getListaPersonagens"

    @Test
    void deveRetornarAListaDePersonagens() {

            Mono<List<PersonagemResponse>> listaMono = Mono.just(client.getPersonagensSalvos());

            when(listaMono).thenReturn(new ArrayList<>());

            assertEquals(client.getListaPersonagens(), new ArrayList<>());

            
        }


    // Teste unitário da função "postPersonagemById"

    @Test
    void deveSalvarPersonagemDaAPINaListaPeloID(){
        PersonagemResponse personagem = PersonagemResponse.builder().id(113).build();

        client.postPersonagemById(personagem.id);

        PersonagemResponse personagemLista = client.getPersonagemFromListById(personagem.id);

        when(client.getPersonagemById(personagem.getId())).thenReturn(personagem);

        assertEquals(personagemLista.getId(), personagem.getId());


    }

    // Teste unitário da função "postPersonagemByName"

    @Test
    void deveSalvarPersonagemDaAPINaListaPeloNome(){
        PersonagemResponse personagem = PersonagemResponse.builder().name("Bandō").build();

        client.postPersonagemByName(personagem.name);

        PersonagemResponse personagemLista = client.getPersonagemFromListByName(personagem.name);

        when(client.getPersonagemByName(personagem.getName())).thenReturn(personagem);

        assertEquals(personagemLista.getName(), personagem.getName());


    }

    // Teste unitário da função "postNewPersonagem"

    @Test
    void deveCriarESalvarUmNovoPersonagemNaLista(){
        PersonagemResponse personagem = PersonagemResponse.builder().name("Narutinho").id(1600).jutsu(new ArrayList<>(List.of("Hazenga"))).natureType(new ArrayList<>(List.of("Hokague"))).tools(new ArrayList<>(List.of("Shuriken"))).build();
        Personal personal = new Personal();
        personagem.setPersonal(personal);

        personal.setAge(17);
        personal.setClan("Kakei");
        personal.setSex("Masculino");


        client.postNewPersonagem("Narutinho", 1600, "Masculino", 17, "Kakei", new ArrayList<>(List.of("Hazenga")), new ArrayList<>(List.of("Hokague")), new ArrayList<>(List.of("Shuriken")));

        PersonagemResponse personagemLista = client.getPersonagemFromListByName(personagem.name);

        Mono<PersonagemResponse> personagemMono = Mono.just(personagemLista);

        when(personagemMono).thenReturn(personagem);

        assertEquals(personagemLista.getName(), personagem.getName());
        assertEquals(personagemLista.getId(), personagem.getId());


    }


    // Teste unitário da função "deletePersonagemById"

    @Test
    void deveDeletarUmPersonagemNaListaPeloId(){

        client.postPersonagemById(55);
        client.deletePersonagemById(55);

        Mono<List<PersonagemResponse>> listaMono = Mono.just(client.getPersonagensSalvos());

        when(listaMono).thenReturn(new ArrayList<>());

        assertEquals(client.getListaPersonagens(), new ArrayList<>());


    }






}