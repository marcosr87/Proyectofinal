package com.company.Exceptions;

/**
 * Created by alumno on 06/06/2018.
 */
public class RestInternalServerErrorException extends RuntimeException {

    public RestInternalServerErrorException(String message) {
        super(message);
    }
}

