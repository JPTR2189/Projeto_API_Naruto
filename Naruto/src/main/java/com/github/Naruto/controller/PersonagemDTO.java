package com.github.Naruto.controller;

import lombok.Getter;

import java.lang.reflect.Array;


public class PersonagemDTO {

    private String id;
    private String name;
    private String images;
    private Object debut;
    private Object personal;
    private Object family;
    private Array jutsu;
    private Array natureType;
    private Array uniqueTraits;
    private Object voiceActors;

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

    public Object getDebut() {
        return debut;
    }

    public void setDebut(Object debut) {
        this.debut = debut;
    }

    public Object getPersonal() {
        return personal;
    }

    public void setPersonal(Object personal) {
        this.personal = personal;
    }

    public Object getFamily() {
        return family;
    }

    public void setFamily(Object family) {
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

    public Object getVoiceActors() {
        return voiceActors;
    }

    public void setVoiceActors(Object voiceActors) {
        this.voiceActors = voiceActors;
    }
}