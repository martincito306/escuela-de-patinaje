package com.patinaje.v1.service;

import com.patinaje.v1.model.Instructor;
import com.patinaje.v1.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    
    @Autowired
    private InstructorRepository instructorRepository;
    
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }
    
    public List<Instructor> findAllActivos() {
        return instructorRepository.findByActivoTrue();
    }
    
    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }
    
    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
    
    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
    
    public Instructor findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }
    
    public List<Instructor> findByEspecialidad(String especialidad) {
        return instructorRepository.findByEspecialidad(especialidad);
    }
}
