package com.api.springapirest.mapper;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("employee_mapper")
@Slf4j
public class EmployeeMapper implements IEmployeeMapper {

    public EmployeeResponseDTO toDTO(final Employee employee) {
        log.info("Start of mapping from entity to response");
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setMail(employee.getMail());
        dto.setActive(employee.getActive());
        dto.setRole(employee.getRole());
        dto.setSalary(employee.getSalary());
        dto.setDate(employee.getDate());
        log.info("End of mapping from entity to response");
        return dto;
    }

    public Employee createReqtoEntity(final EmployeeCreateDTO dto) {
        log.info("Start of mapping from create employee to entity");
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setMail(dto.getMail());
        employee.setActive(dto.getActive());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        employee.setPassword(dto.getPassword());
        log.info("End of mapping from create employee to entity");
        return employee;
    }

    public Employee updateReqtoEntity(final Long id, final EmployeeUpdateDTO dto) {
        log.info("Start of mapping from update employee to entity with id: {}", id);
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setMail(dto.getMail());
        employee.setActive(dto.getActive());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        employee.setPassword(dto.getPassword());
        log.info("End of mapping from update employee to entity with id: {}", id);
        return employee;
    }
}
