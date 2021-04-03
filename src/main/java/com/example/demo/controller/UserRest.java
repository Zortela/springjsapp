package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.mapping.Mapping;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.security.UserDetailsServiceImp;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/users")
public class UserRest {

    private final UserService userService;
    private final UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public UserRest(UserService userService, UserDetailsServiceImp userDetailsServiceImp) {
        this.userService = userService;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> ListAllUsers() {
        List<User> ListUsers = userService.findAll();
        List<UserDto> userDtos = ListUsers.stream().map(Mapping::mapToUserDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping("/active")
    public ResponseEntity<UserDto> getActive() {
        User userActive = userDetailsServiceImp.getUser();
        UserDto userDtoActive = Mapping.mapToUserDto(userActive);
        return ResponseEntity.ok().body(userDtoActive);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        UserDto userDto = Mapping.mapToUserDto(user);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(user, id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> ListRoles() {
        List<Role> roles = userService.getRoles();
        return ResponseEntity.ok().body(roles);
    }
}