package com.example.cruddemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
  public User findByUsername(String username);
}
