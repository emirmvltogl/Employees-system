package com.example.cruddemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
  Role findByName(String name);
  
}
