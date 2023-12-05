package com.example.github.jptr2189.Naruto.exceptions;

// Define uma exceção personalizada para quando o personagem já existir na lista

public class PersonagemExisteException extends RuntimeException{

    public PersonagemExisteException(String mensagem){

        super(mensagem);

    }

}
