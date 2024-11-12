package com.example.cruddemo.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.repo.EmployeeRepo;

@Service
public class EmployeeService {
  

  private EmployeeRepo employeeRepo;

  @Autowired
  public EmployeeService(EmployeeRepo employeeRepo) {
    this.employeeRepo = employeeRepo;
  }

  public List<Employee> getAllEmployees(){
    return employeeRepo.findAll();
  }

  public Employee findById(int id){
    Employee tempEmployee = employeeRepo.findById(id).get();
    return tempEmployee;
  }

  public String deleteEmployee(int id){
    Employee tempEmployee = employeeRepo.findById(id).get();
    if (tempEmployee ==null) {
      throw new RuntimeErrorException(null, "Employee not found 404");
    }
    employeeRepo.deleteById(id);
    return "deleted id : "+id;
  }

  public Employee addEmployee(Employee employee){
    Employee tempEmployee = employeeRepo.save(employee);
    return tempEmployee;
  }

  public Employee updateEmployee(int id ,Employee employee){
    Employee tempEmployee= employeeRepo.findById(id).get();
    tempEmployee.setFirstName(employee.getFirstName());
    tempEmployee.setLastName(employee.getLastName());
    tempEmployee.setEmail(employee.getEmail());
    employeeRepo.save(employee);
    return employee;
  }
}
