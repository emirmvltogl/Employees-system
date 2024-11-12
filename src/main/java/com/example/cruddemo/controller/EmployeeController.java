package com.example.cruddemo.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
  
  private EmployeeService emplyeeService;

  @Autowired
  public EmployeeController(EmployeeService emplyeeService) {
    this.emplyeeService = emplyeeService;
  }

  @GetMapping("/employees")
  public ResponseEntity<List<Employee>> getAllEmployee(){
    return new ResponseEntity<>(emplyeeService.getAllEmployees(),HttpStatus.OK);
  }

  @GetMapping("/employees/{id}")
  public Employee getSingleEmployee(@PathVariable int id ){
    Employee tempEmployee = emplyeeService.findById(id);
    if (tempEmployee == null){
      throw new RuntimeErrorException(null, "Employee not found 404");
    }
    return tempEmployee;
  }

  @PostMapping("/employees")
  public ResponseEntity<Employee> createEmployee (@RequestBody Employee employee){
    Employee dbEmployee = emplyeeService.addEmployee(employee);
    return new ResponseEntity<>(dbEmployee,HttpStatus.OK);
  }

  @PutMapping("/employees/{id}")
  public Employee updateEmployee(@PathVariable int id , @RequestBody Employee employee){
    Employee dbEmployee = emplyeeService.updateEmployee(id, employee);
    return dbEmployee;
  }

  @DeleteMapping("/employees/{id}")
  public String deleteEmployee(@PathVariable int id){
    emplyeeService.deleteEmployee(id);
    return "deleted employee id : "+id;
  }

}
