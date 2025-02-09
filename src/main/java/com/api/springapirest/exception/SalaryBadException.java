package com.api.springapirest.exception;

public class SalaryBadException extends BadRequestException {

    public SalaryBadException(String value) {
        super("salary", "employee", value);
    }
}
