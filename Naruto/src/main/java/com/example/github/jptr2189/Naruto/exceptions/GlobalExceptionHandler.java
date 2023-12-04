package com.example.github.jptr2189.Naruto.exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


// Define uma classe para manipulação global de exceções da aplicação

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonagemNaoEncontradoException.class)

    protected ResponseEntity<Object> handlePersonagemNaoEncontradoException(

            PersonagemNaoEncontradoException ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getDescription(false));

        return handleExceptionInternal(

                ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
}

