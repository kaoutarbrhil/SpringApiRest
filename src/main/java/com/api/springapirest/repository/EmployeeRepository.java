package com.api.springapirest.repository;

import com.api.springapirest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("employee_repository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailAndPassword(String email, String password);
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Employee> findByFirstNameAndLastNameAndIdNot(String firstName, String lastName, Long id);
}