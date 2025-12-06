package com.patinaje.v1.service;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.model.Instructor;
import com.patinaje.v1.model.Estudiante;
import com.patinaje.v1.repository.UsuarioRepository;
import com.patinaje.v1.repository.InstructorRepository;
import com.patinaje.v1.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registrarEstudiante(String nombre, String apellido, String email, 
                                   String telefono, String username, String password, String acudiente) {
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setFirstName(nombre);
        usuario.setLastName(apellido);
        usuario.setEmail(email);
        usuario.setHandle(telefono);
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol("ESTUDIANTE");
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDateTime.now());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        
        // Crear estudiante
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        estudiante.setTelefono(telefono);
        estudiante.setAcudiente(acudiente);
        estudiante.setFechaInicio(LocalDateTime.now());
        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setActivo(true);
        
        estudianteRepository.save(estudiante);
    }

    @Transactional
    public void registrarInstructor(String nombre, String apellido, String email, 
                                    String telefono, String username, String password) {
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setFirstName(nombre);
        usuario.setLastName(apellido);
        usuario.setEmail(email);
        usuario.setHandle(telefono);
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRol("INSTRUCTOR");
        usuario.setActivo(true);
        usuario.setFechaRegistro(LocalDateTime.now());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        
        // Crear instructor
        Instructor instructor = new Instructor();
        instructor.setNombre(nombre);
        instructor.setApellido(apellido);
        instructor.setEmail(email);
        instructor.setTelefono(telefono);
        instructor.setFechaContratacion(LocalDateTime.now());
        instructor.setFechaRegistro(LocalDateTime.now());
        instructor.setActivo(true);
        
        instructorRepository.save(instructor);
    }

    public boolean usuarioExistePorUsername(String username) {
        return usuarioRepository.findByUsername(username) != null;
    }

    public boolean usuarioExistePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}
