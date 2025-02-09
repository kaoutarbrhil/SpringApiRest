package com.api.springapirest.service;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;


public interface IEmployeeService extends IUserService<EmployeeCreateDTO, EmployeeUpdateDTO, EmployeeResponseDTO, Long>{

}
