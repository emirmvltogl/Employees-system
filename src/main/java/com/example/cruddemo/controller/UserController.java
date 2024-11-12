package com.example.cruddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.User;
import com.example.cruddemo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
  
  
  private UserService userService;

  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

  @GetMapping("/user{username}")
  public ResponseEntity<User> getSingleUser(String username){
    return new ResponseEntity<>(userService.findUserByName(username),HttpStatus.OK);
  }

  @PostMapping("/user/register")
  public ResponseEntity<User> createUser(@RequestBody User user){
    User savedUser = userService.save(user);
    return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
  }
}
