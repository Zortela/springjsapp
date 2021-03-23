package com.example.demo.dao;


import com.example.demo.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User getUser(Long id);
   void updateUser(User user);
   void deleteUser(Long id);
   User loadUserByUsername(String username);
}
