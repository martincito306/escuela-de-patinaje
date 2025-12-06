package com.patinaje.v1.repository;

import com.patinaje.v1.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    Optional<Estudiante> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
