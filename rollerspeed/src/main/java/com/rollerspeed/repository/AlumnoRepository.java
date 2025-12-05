package com.rollerspeed.repository;

import com.rollerspeed.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Esta interface maneja todas las operaciones de base de datos para Alumno
// No necesitás escribir SQL, Spring lo hace automáticamente
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Buscar un alumno por su correo
    Optional<Alumno> findByCorreo(String correo);

    // Verificar si ya existe un correo registrado
    boolean existsByCorreo(String correo);
}
