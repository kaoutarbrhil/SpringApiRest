package com.api.springapirest.exception;

public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException(String firstName, String lastName) {
        super("An employee with the first name '" + firstName + "' and last name '" + lastName + "' already exists.");
    }
}
