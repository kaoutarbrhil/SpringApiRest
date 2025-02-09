package com.api.springapirest.repository;

import com.api.springapirest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("employee_repository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}