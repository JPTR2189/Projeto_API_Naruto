package com.example.github.jptr2189.Naruto.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// Definindo Getters e Setters
@Getter
@Setter

// Cria uma classe para personalizar o erro exibido no Swagger


public class ErrorResponse {

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;


    // Define um construtor padrão
    public ErrorResponse() {
    }


    // Define um construtor personalizado
    public ErrorResponse(Date timestamp, int status, String error, String message, String path) {

        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;

    }


}