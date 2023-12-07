package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

// Define um DTO para armazenar os dados recebidos da API externa

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemResponse implements Serializable {

    @JsonProperty("name")
    public String name;
    @JsonProperty("id")
    public int id;
    @JsonProperty("personal")
    public Personal personal;
    @JsonProperty("jutsu")
    public ArrayList<String> jutsu;
    @JsonProperty("natureType")
    public ArrayList<String> natureType;
    @JsonProperty("tools")
    public ArrayList<String> tools;


}