package com.patinaje.v1.service;

import com.patinaje.v1.model.User;
import com.patinaje.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public List<User> findAllActivos() {
        return userRepository.findByActivoTrue();
    }
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    public User save(User user) {
        if (user.getFechaRegistro() == null) {
            user.setFechaRegistro(LocalDateTime.now());
        }
        return userRepository.save(user);
    }
    
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> findByRol(String rol) {
        return userRepository.findByRol(rol);
    }
}
