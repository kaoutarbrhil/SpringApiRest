package com.api.springapirest.service;

public interface IUserService<CreateDTO, UpdateDTO, ResponseDTO, ID> extends IService<CreateDTO, UpdateDTO, ResponseDTO, ID>{

    void login(String username, String password);
    void logout();
}
