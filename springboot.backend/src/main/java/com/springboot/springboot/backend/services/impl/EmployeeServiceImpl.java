package com.springboot.springboot.backend.services.impl;

import com.springboot.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.springboot.backend.model.Employee;
import com.springboot.springboot.backend.repository.EmployeeRepository;
import com.springboot.springboot.backend.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service for save employee restAPI

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //    Use constructor based dependency when mandatory parameters
//    use setter based dependency when optional parameters


//    in a class which is configured as a spring bean &if we have only one constructor the @Autowired
//    annotation can be omitted and spring will use that constructor and inject all necessary dependency
    private EmployeeRepository employeeRepository;
// using constructor based dependency injection to inject EmployeeRepository into EmployeeService implementation class
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

//    method for getAllEmployees REST api
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    //method to get employee by id
    @Override
    public Employee getEmployeeById(long id) {

        Optional<Employee> employee = employeeRepository.findById(id);
        // check if optional object contains employee object or not
        //if not then throw new exception
//        if(employee.isPresent()){
//            return employee.get();
//        }
//        else{
//            throw new ResourceNotFoundException("Employee","Id",id);
//        }

        // using lambda expression

        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //we need to check whether an employee with given id exists or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","Id",id));

        // swapping of data
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        //save existing employee to database
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public Employee deleteEmployee(long id) {
        //check whether the employee exists in the database or not
        Employee employeeToDelete= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
        return employeeToDelete;
//        I added a variable employeeToDelete to store the employee before it gets deleted.
    }


}
