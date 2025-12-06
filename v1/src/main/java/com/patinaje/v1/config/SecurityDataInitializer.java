package com.patinaje.v1.config;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.model.Instructor;
import com.patinaje.v1.model.Estudiante;
import com.patinaje.v1.repository.UsuarioRepository;
import com.patinaje.v1.repository.InstructorRepository;
import com.patinaje.v1.repository.EstudianteRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityDataInitializer {

    @Bean
    CommandLineRunner initDefaultUsers(UsuarioRepository usuarioRepository,
                                      InstructorRepository instructorRepository,
                                      EstudianteRepository estudianteRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                // Crear admin
                if (usuarioRepository.findByUsername("admin") == null) {
                    Usuario admin = new Usuario();
                    admin.setUsername("admin");
                    admin.setPassword(passwordEncoder.encode("admin123"));
                    admin.setEmail("admin@clubpaen.com");
                    admin.setFirstName("Administrador");
                    admin.setLastName("Sistema");
                    admin.setHandle("300-000-0000");
                    admin.setRol("ADMIN");
                    admin.setActivo(true);
                    admin.setFechaRegistro(LocalDateTime.now());
                    usuarioRepository.save(admin);
                    System.out.println("✓ Usuario ADMIN creado");
                }

                // Crear instructor
                if (usuarioRepository.findByUsername("instructor") == null) {
                    Usuario instrUsuario = new Usuario();
                    instrUsuario.setUsername("instructor");
                    instrUsuario.setPassword(passwordEncoder.encode("instructor123"));
                    instrUsuario.setEmail("instructor@clubpaen.com");
                    instrUsuario.setFirstName("Juan");
                    instrUsuario.setLastName("Instructor");
                    instrUsuario.setHandle("300-111-1111");
                    instrUsuario.setRol("INSTRUCTOR");
                    instrUsuario.setActivo(true);
                    instrUsuario.setFechaRegistro(LocalDateTime.now());
                    Usuario instrGuardado = usuarioRepository.save(instrUsuario);
                    
                    // Crear registro en instructores
                    if (instructorRepository.findByEmail("instructor@clubpaen.com") == null) {
                        Instructor instructor = new Instructor();
                        instructor.setNombre("Juan");
                        instructor.setApellido("Instructor");
                        instructor.setEmail("instructor@clubpaen.com");
                        instructor.setTelefono("300-111-1111");
                        instructor.setFechaContratacion(LocalDateTime.now());
                        instructor.setFechaRegistro(LocalDateTime.now());
                        instructor.setActivo(true);
                        instructorRepository.save(instructor);
                    }
                    System.out.println("✓ Usuario INSTRUCTOR creado");
                }

                // Crear estudiante
                if (usuarioRepository.findByUsername("estudiante") == null) {
                    Usuario estUsuario = new Usuario();
                    estUsuario.setUsername("estudiante");
                    estUsuario.setPassword(passwordEncoder.encode("estudiante123"));
                    estUsuario.setEmail("estudiante@clubpaen.com");
                    estUsuario.setFirstName("Maria");
                    estUsuario.setLastName("Estudiante");
                    estUsuario.setHandle("300-222-2222");
                    estUsuario.setRol("ESTUDIANTE");
                    estUsuario.setActivo(true);
                    estUsuario.setFechaRegistro(LocalDateTime.now());
                    Usuario estGuardado = usuarioRepository.save(estUsuario);
                    
                    // Crear registro en estudiantes
                    if (estudianteRepository.findByEmail("estudiante@clubpaen.com").isEmpty()) {
                        Estudiante estudiante = new Estudiante();
                        estudiante.setNombre("Maria");
                        estudiante.setApellido("Estudiante");
                        estudiante.setEmail("estudiante@clubpaen.com");
                        estudiante.setTelefono("300-222-2222");
                        estudiante.setAcudiente("Pedro Acudiente");
                        estudiante.setFechaInicio(LocalDateTime.now());
                        estudiante.setFechaRegistro(LocalDateTime.now());
                        estudiante.setActivo(true);
                        estudianteRepository.save(estudiante);
                    }
                    System.out.println("✓ Usuario ESTUDIANTE creado");
                }
                
                System.out.println("✓ Inicialización de usuarios completada");

            } catch (Exception e) {
                System.err.println("Error al inicializar usuarios: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
