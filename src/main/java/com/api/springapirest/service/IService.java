package com.api.springapirest.service;


import java.util.List;
import java.util.Optional;

public interface IService<CreateDTO, UpdateDTO, ResponseDTO, ID> {

    Optional<ResponseDTO> getEmployeeById(ID id);
    List<ResponseDTO> getEmployees(int numPage, int limite);
    void deleteEmployee(ID id);
    ResponseDTO saveEmployee(CreateDTO createDTO);
    Optional<ResponseDTO> updateEmployee(ID id, UpdateDTO updateDTO);
}
