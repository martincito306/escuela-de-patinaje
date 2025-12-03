package com.patinaje.v1.repository;

import com.patinaje.v1.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
    
    // Buscar programas por nivel
    List<Programa> findByNivel(String nivel);
    
    // Buscar programas activos
    List<Programa> findByActivoTrue();
    
    // Buscar por rango de edad
    List<Programa> findByEdadMinLessThanEqualAndEdadMaximaGreaterThanEqual(Integer edad, Integer edad2);
}
