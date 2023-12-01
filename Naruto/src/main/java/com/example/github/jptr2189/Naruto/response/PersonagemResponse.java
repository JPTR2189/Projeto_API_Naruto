package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

// Define um DTO para armazenar os dados recebidos da API externa
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

// Define os metódos dos atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Objects getDebut() {
        return debut;
    }

    public void setDebut(Objects debut) {
        this.debut = debut;
    }

    public Objects getPersonal() {
        return personal;
    }

    public void setPersonal(Objects personal) {
        this.personal = personal;
    }

    public Objects getFamily() {
        return family;
    }

    public void setFamily(Objects family) {
        this.family = family;
    }

    public Array getJutsu() {
        return jutsu;
    }

    public void setJutsu(Array jutsu) {
        this.jutsu = jutsu;
    }

    public Array getNatureType() {
        return natureType;
    }

    public void setNatureType(Array natureType) {
        this.natureType = natureType;
    }

    public Array getUniqueTraits() {
        return uniqueTraits;
    }

    public void setUniqueTraits(Array uniqueTraits) {
        this.uniqueTraits = uniqueTraits;
    }

    public Objects getVoiceActors() {
        return voiceActors;
    }

    public void setVoiceActors(Objects voiceActors) {
        this.voiceActors = voiceActors;
    }
}