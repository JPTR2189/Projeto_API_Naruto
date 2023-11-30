package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Family {
    @JsonProperty("father")
    public String father;
    @JsonProperty("mother")
    public String mother;
    @JsonProperty("son")
    public String son;
    @JsonProperty("daughter")
    public String daughter;
    @JsonProperty("wife")
    public String wife;
    @JsonProperty("godfather")
    public String godfather;

}
