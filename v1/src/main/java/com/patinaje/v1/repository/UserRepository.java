package com.patinaje.v1.repository;

import com.patinaje.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
    User findByEmail(String email);
    
    List<User> findByActivoTrue();
    
    List<User> findByRol(String rol);
}
