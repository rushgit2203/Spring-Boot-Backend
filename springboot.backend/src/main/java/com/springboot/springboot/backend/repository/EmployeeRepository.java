package com.springboot.springboot.backend.repository;

import com.springboot.springboot.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//internally provides annotation @Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String firstName);
    Employee findByLastName(String lastName);
}
