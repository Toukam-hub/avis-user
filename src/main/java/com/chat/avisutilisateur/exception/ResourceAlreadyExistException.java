package com.chat.avisutilisateur.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String message){
        super(message);
    }
}
