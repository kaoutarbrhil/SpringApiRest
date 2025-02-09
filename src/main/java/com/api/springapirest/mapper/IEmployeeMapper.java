package com.api.springapirest.mapper;


import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.entity.Employee;

public interface IEmployeeMapper extends IMapper<EmployeeCreateDTO, EmployeeUpdateDTO, EmployeeResponseDTO, Employee, Long> {
}
