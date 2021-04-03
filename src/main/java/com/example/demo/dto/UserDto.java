package com.example.demo.dto;

import com.example.demo.model.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Byte age;
    private List<RoleDto> roles;
}
