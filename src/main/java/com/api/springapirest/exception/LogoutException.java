package com.api.springapirest.exception;

public abstract class LogoutException extends RuntimeException {

  public LogoutException(String object, String value) {
    super(object + " logout failed for: " + value);
  }
}
