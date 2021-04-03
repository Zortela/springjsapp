package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User getUser(Long id);

    void updateUser(User user, Long id);

    void deleteUser(Long id);

    List<Role> getRoles();
}
