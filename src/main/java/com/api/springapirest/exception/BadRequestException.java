package com.api.springapirest.exception;



public abstract class BadRequestException extends RuntimeException {

  public BadRequestException(String field, String objet, String value) {
    super("The value of "+field+" '"+value + " is not valid in " + objet);
  }

}
