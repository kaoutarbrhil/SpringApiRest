package com.api.springapirest.service;

public interface IUserService<CreateDTO, UpdateDTO, ResponseDTO, ID> extends IService<CreateDTO, UpdateDTO, ResponseDTO, ID>{

    ResponseDTO login(String email, String password);
    void logout(String email);
}
