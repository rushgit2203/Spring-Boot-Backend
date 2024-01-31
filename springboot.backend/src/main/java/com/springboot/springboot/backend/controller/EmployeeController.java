package com.springboot.springboot.backend.controller;

import com.springboot.springboot.backend.exception.ResourceNotFoundException;
import com.springboot.springboot.backend.model.Employee;
import com.springboot.springboot.backend.services.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("all")
    public List<Employee>getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping
    public ResponseEntity <Employee> getEmployeeByFirstName(@RequestParam (value = "firstName") String firstName){
        return new ResponseEntity<Employee>( employeeService.getEmployeeByFirstName(firstName),HttpStatus.OK);
    }


    @GetMapping("byLastName")
    public ResponseEntity<Employee> getEmployeeByLastName(@RequestParam(value = "lastName") String lastName){
        try {
            Employee foundEmployee = employeeService.getEmployeeByLastName(lastName);
            return  new ResponseEntity<Employee>(foundEmployee,HttpStatus.OK);
        }
        catch (ResourceNotFoundException e)
        {
            throw new ResourceNotFoundException("Employee",lastName,"could not find employee with last name", "customMessage");
//            throw new ResourceNotFoundException("Employee",lastName,"could not find employee with last name", e.getCustomMessage());
        }
    }
//            return  new ResponseEntity<Employee>(employeeService.getEmployeeByLastName(lastName),HttpStatus.OK);


//    @GetMapping("byLastName")
//    public ResponseEntity<Employee> getEmployeeByLastName(@RequestParam(value = "lastName") String lastName) {
//        try {
//            Employee foundEmployee = employeeService.getEmployeeByLastName(lastName);
//            return new ResponseEntity<>(foundEmployee, HttpStatus.OK);
//        } catch (ResourceNotFoundException e) {
//            //  error message for the specific case of searching by last name
//            throw new ResourceNotFoundException("Employee", "Last Name", lastName, "Could not find employee with last name");
//        }
//    }


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
