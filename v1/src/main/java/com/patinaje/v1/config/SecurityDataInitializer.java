package com.patinaje.v1.config;

import com.patinaje.v1.model.User;
import com.patinaje.v1.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityDataInitializer {

    @Bean
    CommandLineRunner initDefaultUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfNotExists(userRepository, passwordEncoder,
                    "admin", "admin@clubpaen.com", "Administrador", "Sistema",
                    "300-000-0000", "ADMIN", "admin123");

            createUserIfNotExists(userRepository, passwordEncoder,
                    "instructor", "instructor@clubpaen.com", "Juan", "Instructor",
                    "300-111-1111", "INSTRUCTOR", "1234");

                createUserIfNotExists(userRepository, passwordEncoder,
                    "estudiante", "estudiante@clubpaen.com", "Maria", "Estudiante",
                    "300-222-2222", "ESTUDIANTE", "1234");
        };
    }

    private void createUserIfNotExists(UserRepository userRepository,
                                       PasswordEncoder passwordEncoder,
                                       String username,
                                       String email,
                                       String nombre,
                                       String apellido,
                                       String telefono,
                                       String rol,
                                       String rawPassword) {
        if (userRepository.findByUsername(username) != null) {
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setRol(rol);
        user.setActivo(true);
        user.setFechaRegistro(LocalDateTime.now());
        userRepository.save(user);
    }
}
