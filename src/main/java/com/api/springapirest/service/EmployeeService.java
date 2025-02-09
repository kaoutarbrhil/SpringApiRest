package com.api.springapirest.service;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.exception.NotFoundEmployeeException;
import com.api.springapirest.exception.SalaryBadException;
import com.api.springapirest.mapper.EmployeeMapper;
import com.api.springapirest.entity.Employee;
import com.api.springapirest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("employee_service")
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private Optional<Employee> findEmployeeById(final Long id) {
        log.info("Find employee by id: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            log.info("Employee not found with id: {} ", id);
            return Optional.empty();
        }
        log.info("Employee found with id: {}", id);
        return Optional.of(employee);
    }

    @Override
    public Optional<EmployeeResponseDTO> getEmployeeById(final Long id) {
        log.info("Start of fetching employee with ID: {}", id);
        Optional<EmployeeResponseDTO> employee = findEmployeeById(id).map(employeeMapper::toDTO);
        log.info("End of fetching employee with ID: {}", id);
        if (employee.isPresent()) {
            return employee;
        }
        throw new NotFoundEmployeeException(id);
    }

    @Override
    public List<EmployeeResponseDTO> getEmployees(final int numPage, final int limite) {
        log.info("Start to fetching all employees: {} par {} page", numPage, limite);
        Pageable pageable = PageRequest.of(numPage-1, limite);
        List<EmployeeResponseDTO> employees = employeeRepository.findAll(pageable).stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
        log.info("End of fetching all employees: {} par {} page", numPage, limite);
        return employees;
    }

    @Override
    public void deleteEmployee(final Long id) {
        log.info("Start of deleting employee with id: {}", id);
        Optional <Employee> employee =  findEmployeeById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            log.info("Deleted employee with id: {}", id);
        }
        log.info("End of deleting employee with id: {}", id);
        throw new NotFoundEmployeeException(id);
    }

    @Override
    public EmployeeResponseDTO saveEmployee(final EmployeeCreateDTO employeeCreateDTO) {
        log.info("Start to create a new employee");
        if (employeeCreateDTO.getSalary()<3000) {
            log.info("Validation field salary is not valid in employeeCreateDTO");
            throw new SalaryBadException(employeeCreateDTO.getSalary().toString());
        }

        Employee employee = employeeMapper.createReqtoEntity(employeeCreateDTO);
        employee.setDate(new Date());
        EmployeeResponseDTO employeeResponseDTO = employeeMapper.toDTO(employeeRepository.save(employee));
        log.info("End to create a new employee");
        return employeeResponseDTO;
    }

    @Override
    public Optional<EmployeeResponseDTO> updateEmployee(final Long id, final EmployeeUpdateDTO employeeUpdateDTO) {
        log.info("Start of updating employee with id: {}", id);
        Optional <Employee> employeeById =  findEmployeeById(id);

        if (employeeById.isEmpty()) {
            log.error("End of updating employee, Employee not found with id: {}", id);
            throw new NotFoundEmployeeException(id);
        }
        if (employeeUpdateDTO.getSalary()<3000) {
            log.info("Validation field salary is not valid in employeeUpdateDTO");
            throw new SalaryBadException(employeeUpdateDTO.getSalary().toString());
        }

        Employee employeeMapped = employeeMapper.updateReqtoEntity(id, employeeUpdateDTO);
        employeeMapped.setDate(employeeById.get().getDate());
        Employee updatedEmployee = employeeRepository.save(employeeMapped);
        EmployeeResponseDTO updatedEmployeeResponseDTO = employeeMapper.toDTO(updatedEmployee);
        log.info("End of updating employee, Employee with id {} updated successfully", id);
        return Optional.ofNullable(updatedEmployeeResponseDTO);
    }
}
