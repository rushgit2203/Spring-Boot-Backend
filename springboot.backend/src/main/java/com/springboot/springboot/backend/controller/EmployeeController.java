package com.springboot.springboot.backend.controller;

import com.springboot.springboot.backend.model.Employee;
import com.springboot.springboot.backend.services.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //    @RequestBody annotation allows us to retrieve the request body and
//    automatically convert it to java object

    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
//    build get all employees REST api

    @GetMapping
    public List<Employee>getAllEmployees(){
        return employeeService.getAllEmployees();
    }


    //  build  get employee by id REST api
    //http://localhost:8080/api/employee/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }

//    build update employee REST api
    //http://localhost:8080/api/employee/1
// to create a response for the rest api we use ResponseEntity as a return type
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long  id , @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);

    }

    //build delete employee REST api
    //http://localhost:8080/api/employee/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully...",HttpStatus.OK);

    }
}
