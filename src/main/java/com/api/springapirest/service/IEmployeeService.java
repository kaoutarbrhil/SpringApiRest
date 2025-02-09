package com.api.springapirest.service;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Optional<EmployeeResponseDTO> getEmployeeById(Long id);
    List<EmployeeResponseDTO> getEmployees(int numPage, int limite);
    void deleteEmployee(Long id);
    EmployeeResponseDTO saveEmployee(EmployeeCreateDTO employeeCreateDTO);
    Optional<EmployeeResponseDTO> updateEmployee(Long id, EmployeeUpdateDTO employee);
}
