package com.api.springapirest.controller;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;

public interface IEmployeeController extends IUserController<EmployeeCreateDTO, EmployeeUpdateDTO, EmployeeResponseDTO, Long>{
}
