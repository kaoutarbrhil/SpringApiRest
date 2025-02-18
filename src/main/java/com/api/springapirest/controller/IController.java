package com.api.springapirest.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IController<CreateDTO, UpdateDTO, ResponseDTO, ID> {

    ResponseEntity<List<ResponseDTO>> getAllEmployees(int numPage, int limite);
    ResponseEntity<?> getEmployeeById(ID id);
    ResponseEntity<?> addNewEmployee(CreateDTO createDTO);
    ResponseEntity<?> removeEmployeeById(ID id);
    ResponseEntity<?> updateEmployee(ID id, UpdateDTO updateDTO);

}
