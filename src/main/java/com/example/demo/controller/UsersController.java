//package com.example.demo.controller;
//
//
//import com.example.demo.model.Role;
//import com.example.demo.model.User;
//import com.example.demo.security.UserDetailsServiceImp;
//import com.example.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin/")
//public class UsersController {
//
//    private final UserDetailsServiceImp userDetailsServiceImp;
//    private final UserService userService;
//
//    @Autowired
//    public UsersController(UserDetailsServiceImp userDetailsServiceImp, UserService userService) {
//        this.userDetailsServiceImp = userDetailsServiceImp;
//        this.userService = userService;
//    }
//
//    @GetMapping("")
//    public List<User> getAllUsers() {
//        List<User> users = userService.findAll();
//        return users;
//    }
//
//    @GetMapping("{id}")
//    public User getUser(@PathVariable("id") Long id) {
//        User user = userService.getUser(id);
//        return user;
//    }
//
//    @PostMapping
//    public void createUser(@RequestBody User user) {
//        userService.save(user);
//    }
//
//    @PostMapping("{id}")
//    public void updateUser(@PathVariable("id") Long id, @RequestBody User user) {
//        userService.updateUser(user, id);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//    }
//
//
//    @GetMapping()
//    public String getAllUsers(@ModelAttribute("user") User user,
//                              Model model) {
//        model.addAttribute("admin", userDetailsServiceImp.getUser());
//        model.addAttribute("ListUsers", userService.findAll());
//        List<Role> allRoles = userService.getRoles();
//        model.addAttribute("allRoles", allRoles);
//        return "admin/index";
//    }
//
//    @PostMapping("/users")
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/admin/";
//    }
//
//    @PostMapping("{id}")
//    public String update(@ModelAttribute("user") User user,@PathVariable("id") Long id) {
//        userService.updateUser(user,id);
//        return "redirect:/admin/";
//    }
//
//    @DeleteMapping("{id}/delete")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin/";
//    }
//
//}
