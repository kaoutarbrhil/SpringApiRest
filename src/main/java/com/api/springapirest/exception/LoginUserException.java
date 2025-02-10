package com.api.springapirest.exception;

public class LoginUserException extends LoginException {
  private static final long serialVersionUID = 1L;

  public LoginUserException(String email) {
    super("Login or password incorrect for email: " + email);
  }

}
