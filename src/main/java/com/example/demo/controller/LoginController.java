package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.mapping.Mapping;
import com.example.demo.model.User;
import com.example.demo.security.UserDetailsServiceImp;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final UserService userService;

    @Autowired
    public LoginController(UserDetailsServiceImp userDetailsServiceImp, UserService userService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "service/login";
    }

    @GetMapping("/user/json")
    public ResponseEntity<UserDto> getUser() {
        User userActive = userDetailsServiceImp.getUser();
        UserDto userDtoActive = Mapping.mapToUserDto(userActive);
        return ResponseEntity.ok().body(userDtoActive);
    }

    @GetMapping(value = "/user/{id}")
    public String UserPage(@PathVariable("id") Long id) {
        return "user/getUser";
    }

    @GetMapping("/admin/")
    public String getAllUsers() {
        return "/admin/index";
    }
}