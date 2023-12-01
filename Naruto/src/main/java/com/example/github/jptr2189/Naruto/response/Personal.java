package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Personal {
    @JsonProperty("sex")
    public String sex;
    @JsonProperty("classification")
    public String classification;
    @JsonProperty("occupation")
    public String occupation;
    @JsonProperty("affiliation")
    public ArrayList<String> affiliation;
}
