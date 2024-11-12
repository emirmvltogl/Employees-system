package com.example.cruddemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
  
}
