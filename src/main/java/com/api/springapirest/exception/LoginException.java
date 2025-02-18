package com.api.springapirest.exception;

public abstract class LoginException extends RuntimeException {

    public LoginException(String object) {
        super(object + " login failed !!");
    }
}
