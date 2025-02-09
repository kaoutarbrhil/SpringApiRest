package com.api.springapirest.exception;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String objet, Long id) {
        super(objet + " with id " + id + " not found");
    }

    public NotFoundException(String objet, String string) {
        super(objet + " with string " + string + " not found");
    }
}
