package com.rollerspeed.service;

import com.rollerspeed.model.Alumno;
import com.rollerspeed.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Acá va la lógica de negocio para manejar alumnos
@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Guardar un nuevo alumno
    public Alumno guardarAlumno(Alumno alumno) {
        // Verificamos que el correo no esté repetido
        if (alumnoRepository.existsByCorreo(alumno.getCorreo())) {
            throw new RuntimeException("Ya existe un alumno con ese correo");
        }
        return alumnoRepository.save(alumno);
    }

    // Obtener todos los alumnos
    public List<Alumno> obtenerTodos() {
        return alumnoRepository.findAll();
    }

    // Buscar un alumno por ID
    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    // Buscar por correo
    public Optional<Alumno> buscarPorCorreo(String correo) {
        return alumnoRepository.findByCorreo(correo);
    }

    // Verificar si un correo ya está registrado
    public boolean correoExiste(String correo) {
        return alumnoRepository.existsByCorreo(correo);
    }
}
