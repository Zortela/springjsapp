package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.UserDetailsServiceImp;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String loginPage(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "service/login";
    }

    @GetMapping(value = "/user/{id}")
    public String helloUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(userDetailsServiceImp.getUser().getId());
        model.addAttribute("admin", userDetailsServiceImp.getUser());
        model.addAttribute("user", user);
        return "user/getUser";
    }


}