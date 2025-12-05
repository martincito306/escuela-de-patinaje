package com.patinaje.v1.repository;

import com.patinaje.v1.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
    List<Instructor> findByActivoTrue();
    
    Instructor findByEmail(String email);
    
    List<Instructor> findByEspecialidad(String especialidad);
}
