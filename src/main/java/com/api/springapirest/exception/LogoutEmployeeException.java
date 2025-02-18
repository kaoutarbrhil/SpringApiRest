package com.api.springapirest.exception;

public class LogoutEmployeeException extends LogoutException {
    private static final long serialVersionUID = 1L;

    public LogoutEmployeeException(String email) {
        super("Employee", email);
    }
}
