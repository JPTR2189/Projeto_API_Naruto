package com.example.github.jptr2189.Naruto.client;

import com.example.github.jptr2189.Naruto.response.PersonagemResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
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





    @Test
    void deveBuscarPersonagemPeloIdNaAPI(){

        PersonagemResponse personagem = PersonagemResponse.builder().id(55).build();

        when(client.getPersonagemById(personagem.id)).thenReturn(personagem);

        Mono<PersonagemResponse> retorno = client.getPersonagemById(personagem.id);

        PersonagemResponse retornoSemMono = retorno.block();

        assertEquals(retornoSemMono.getId(), personagem.getId());
        Assertions.assertThat(retorno).isNotNull();



    }

    @Test
    void deveBuscarPersonagemPeloNomeNaAPi(){

        PersonagemResponse personagem = PersonagemResponse.builder().name("Amachi").build();

        when(client.getPersonagemByName(personagem.name)).thenReturn(personagem);

        Mono<PersonagemResponse> retorno = client.getPersonagemByName(personagem.name);

        PersonagemResponse retornoSemMono = retorno.block();

        assertEquals(retornoSemMono.getName(), personagem.getName());
        Assertions.assertThat(retorno).isNotNull();



    }



}
