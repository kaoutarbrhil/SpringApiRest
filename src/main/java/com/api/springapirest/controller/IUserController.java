package com.api.springapirest.controller;

import org.springframework.http.ResponseEntity;

public interface IUserController<CreateDTO, UpdateDTO, ResponseDTO, ID> extends IController<CreateDTO, UpdateDTO, ResponseDTO, ID>{
    ResponseEntity<?> login(String email, String password);
    ResponseEntity<?> logout(String email);

}
