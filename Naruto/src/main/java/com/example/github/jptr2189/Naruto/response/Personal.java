package com.example.github.jptr2189.Naruto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personal implements Serializable {

    @JsonProperty("sex")
    public String sex;
    @JsonProperty("age")
    public int age;
    @JsonProperty("clan")
    public String clan;

}
