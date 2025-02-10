package com.api.springapirest.exception;

public class LogoutUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LogoutUserException(String email) {
        super("Logout failed for user with email: " + email);
    }
}
