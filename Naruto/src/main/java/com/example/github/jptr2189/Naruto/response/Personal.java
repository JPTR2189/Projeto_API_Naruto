package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Personal {

    public String sex;
    public String classification;
    public String occupation;
    public ArrayList<String> affiliation;

}
