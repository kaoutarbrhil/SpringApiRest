package com.api.springapirest.service;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.request.EmployeeUpdateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.exception.*;
import com.api.springapirest.entity.Employee;
import com.api.springapirest.mapper.IEmployeeMapper;
import com.api.springapirest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("employee_service")
@RequiredArgsConstructor
@Slf4j
public class EmployeeService implements IEmployeeService{

    @Autowired
    @Qualifier("employee_repository")
    private final EmployeeRepository employeeRepository;

    @Autowired
    @Qualifier("employee_mapper")
    private final IEmployeeMapper employeeMapper;


    public String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new ImageEmptyException();
        }

        String imageName = image.getOriginalFilename().toLowerCase();
        if (!imageName.endsWith(".png") && !imageName.endsWith(".jpg") && !imageName.endsWith(".svg")) {
            throw new ImageFormatException();
        }

        if (image.getSize() > 5 * 1024 * 1024) {
            throw new ImageSizeException();
        }

        Path directoryPath = Paths.get("src/main/resources/static/images");
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = directoryPath.resolve(fileName);

        image.transferTo(filePath.toFile());
        return directoryPath + "/" + fileName;
    }

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
    @Cacheable(value = "employees", key = "#id")
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
    @Cacheable(value = "employees_list")
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
    @Caching(evict = {
            @CacheEvict(value = "employees", key = "#id"),
            @CacheEvict(value = "employees_list", allEntries = true)
    })
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
    @CacheEvict(value = "employees_list", allEntries = true)
    public EmployeeResponseDTO saveEmployee(final EmployeeCreateDTO employeeCreateDTO) {
        log.info("Start to create a new employee");
        Optional<Employee> existingEmployee = employeeRepository.findByFirstNameAndLastName(employeeCreateDTO.getFirstName(), employeeCreateDTO.getLastName());

        if (existingEmployee.isPresent()) {
            log.info("Employee with firstname {} and lastname {} already exists.", employeeCreateDTO.getFirstName(), employeeCreateDTO.getLastName());
            throw new DuplicateEmployeeException(employeeCreateDTO.getFirstName(), employeeCreateDTO.getLastName());
        }

        if (employeeCreateDTO.getSalary()<3000) {
            log.info("Validation field salary is not valid in employeeCreateDTO");
            throw new SalaryBadException(employeeCreateDTO.getSalary().toString());
        }

        Employee employee = employeeMapper.createReqtoEntity(employeeCreateDTO);
        employee.setDate(new Date());

        if (employeeCreateDTO.getImage() != null && !employeeCreateDTO.getImage().isEmpty()) {
            try {
                String imageUrl = saveImage(employeeCreateDTO.getImage());
                employee.setImageUrl(imageUrl);
            } catch (IOException e) {
                log.error("Error while saving image", e);
                throw new RuntimeException("Failed to save image", e);
            }
        }

        EmployeeResponseDTO employeeResponseDTO = employeeMapper.toDTO(employeeRepository.save(employee));
        log.info("End to create a new employee");
        return employeeResponseDTO;
    }

    @Override
    @CachePut(value = "employees", key = "#id")
    public Optional<EmployeeResponseDTO> updateEmployee(final Long id, final EmployeeUpdateDTO employeeUpdateDTO) {
        log.info("Start of updating employee with id: {}", id);
        Optional <Employee> employeeById =  findEmployeeById(id);

        if (employeeById.isEmpty()) {
            log.error("End of updating employee, Employee not found with id: {}", id);
            throw new NotFoundEmployeeException(id);
        }

        Optional<Employee> existingEmployee = employeeRepository.findByFirstNameAndLastNameAndIdNot(employeeUpdateDTO.getFirstName(), employeeUpdateDTO.getLastName(), id);
        if (existingEmployee.isPresent()) {
            log.info("Employee with firstname {} and lastname {} already exists.", employeeUpdateDTO.getFirstName(), employeeUpdateDTO.getLastName());
            throw new DuplicateEmployeeException( employeeUpdateDTO.getFirstName(), employeeUpdateDTO.getLastName());
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

    @Override
    public EmployeeResponseDTO login(String email, String password) {
        log.info("Start of login for user: {}", email);
        Optional<Employee> employeeOptional = employeeRepository.findByEmailAndPassword(email, password);

        if (employeeOptional.isEmpty()) {
            log.warn("End of login, Login failed for user: {}", email);
            throw new LoginEmployeeException();
        }

        employeeOptional.get().setActive(true);
        employeeRepository.save(employeeOptional.get());
        EmployeeResponseDTO employee = employeeMapper.toDTO(employeeOptional.get());
        log.info("User {} logged in successfully", email);
        return employee;
    }

    @Override
    public void logout(String email) {
        log.info("Start of logout for user: {}", email);
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);

        if (employeeOptional.isEmpty()) {
            log.error("End of logout, Logout failed for user: {}", email);
            throw new LogoutEmployeeException(email);
        }
        employeeOptional.get().setActive(false);
        employeeRepository.save(employeeOptional.get());
        log.info("End of logout, User {} logged out successfully", email);
    }

}
