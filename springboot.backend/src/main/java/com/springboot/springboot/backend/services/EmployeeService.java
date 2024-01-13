package com.springboot.springboot.backend.services;

import com.springboot.springboot.backend.model.Employee;

import java.util.List;

public interface EmployeeService {
Employee saveEmployee(Employee employee);
List<Employee>getAllEmployees();
Employee getEmployeeById(long id);

Employee updateEmployee(Employee employee,long id);

Employee deleteEmployee(long id);

// in interfaces, we can only have abstract methods and variables
    // we can not have the method body
    //it represents an is-a relationship
    // used to achieve abstraction
    // multiple inheritance
    // loose coupling-
    //


}
