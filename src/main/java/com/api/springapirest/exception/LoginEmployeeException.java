package com.api.springapirest.exception;

public class LoginEmployeeException extends LoginException {
    private static final long serialVersionUID = 1L;

    public LoginEmployeeException() {
        super("Employee");
    }
}
