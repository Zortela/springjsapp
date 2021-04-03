package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) throws EntityNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(("id= ") + id));
        return user;
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User updatedUser, Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(("id= ") + id));
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(("id = ") + id));
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
