package com.api.springapirest.exception;

public class NotFoundEmployeeException extends NotFoundException {
    private static final long serialVersionUID = 1L;

    public NotFoundEmployeeException(final Long id) {
        super("Employee", id);
    }

    public NotFoundEmployeeException(final String string) {
        super("Employee", string);
    }

}
