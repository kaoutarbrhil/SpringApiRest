package com.api.springapirest.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class BadRequestException extends RuntimeException {
  private final Map<String, String> errors;

  public BadRequestException(Map<String, String> errors) {
    super("Validation errors");
    this.errors = errors;
  }

}
