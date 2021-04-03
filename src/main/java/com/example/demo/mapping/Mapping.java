package com.example.demo.mapping;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapping {

    public static RoleDto mapToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setAge(user.getAge());
        List<RoleDto> rolesDto = user.getRoles().stream().map(role -> mapToRoleDto(role)).collect(Collectors.toList());
        userDto.setRoles(rolesDto);
        return userDto;
    }
}
