package com.example.cruddemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.cruddemo.entity.User;

@Service
public interface UserService extends UserDetailsService{

  User findUserByName(String username);
  User save (User user);
} 
