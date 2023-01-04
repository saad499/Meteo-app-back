package com.brightcoding.app.ws.meteo.app.ws.exceptions;

public class UserException extends RuntimeException{

    private static final long serialVersionUID = -1097099662831065059L;

    public UserException(String message){
        super(message);
    }
}
