package com.example.cruddemo.service;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cruddemo.entity.User;
import com.example.cruddemo.repo.RoleRepo;
import com.example.cruddemo.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

  private UserRepo userRepo;

  private RoleRepo roleRepo;

  private PasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder encoder) {
    this.userRepo = userRepo;
    this.roleRepo = roleRepo;
    this.encoder = encoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User tempUser = userRepo.findByUsername(username);
    if (tempUser == null) {
      throw new UsernameNotFoundException("username is not found 404");
    }
    return new org.springframework.security.core.userdetails.User(tempUser.getUsername(), tempUser.getPassword(),
        mapRolesToAuthorities(tempUser.getRoles()));
  }

  @Override
  public User findUserByName(String username) {
    User tempUser = userRepo.findByUsername(username);
    return tempUser;
  }

  @Override
  @Transactional
  public User save(User user) {
    // Kullanıcı kaydı için gerekli işlemler
    // Örneğin: şifreyi hash'lemek isteyebilirsiniz
    String hashedPassword = encoder.encode(user.getPassword());
    user.setPassword(hashedPassword);
    if (user.getRoles() != null) {
      user.getRoles().forEach(role -> {
        if (role.getId() != null) {
          com.example.cruddemo.entity.Role existingRole = roleRepo.findByName(role.getName());
          if (existingRole == null) {
            roleRepo.save(role);

          } else {
            role.setId(existingRole.getId());
          }
        }
      });
    }
    
    return userRepo.save(user);
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
      Collection<com.example.cruddemo.entity.Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}