package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoiceActors {
    private String japanese;
    private String english;

}
