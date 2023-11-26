package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonagemResponse {

    private String id;
    private String name;
    private String images;
    private Objects debut;
    private Objects personal;
    private Objects family;
    private Array jutsu;
    private Array natureType;
    private Array uniqueTraits;
    private Objects voiceActors;





}