package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

// Define um DTO para armazenar os dados recebidos da API externa

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class PersonagemResponse {

    @JsonProperty("debut")
    public Debut debut;
    @JsonProperty("personal")
    public Personal personal;
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("images")
    public ArrayList<String> images;
    @JsonProperty("jutsu")
    public ArrayList<String> jutsu;
    @JsonProperty("natureType")
    public ArrayList<String> natureType;
    @JsonProperty("tools")
    public ArrayList<String> tools;
    @JsonProperty("voiceActors")
    public VoiceActors voiceActors;
    @JsonProperty("family")
    public Family family;


}