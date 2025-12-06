package com.patinaje.v1.config;

import com.patinaje.v1.model.User;
import com.patinaje.v1.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityDataInitializer {

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("^\\$2[aby]\\$.*");

    @Bean
    CommandLineRunner seedDefaultUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createOrUpdateUser(userRepository, passwordEncoder,
                    "admin", "admin@clubpaen.com", "Administrador", "Sistema",
                    "300-000-0000", "ADMIN", "admin123");

            createOrUpdateUser(userRepository, passwordEncoder,
                    "instructor", "instructor@clubpaen.com", "Juan", "Instructor",
                    "300-111-1111", "INSTRUCTOR", "1234");

            createOrUpdateUser(userRepository, passwordEncoder,
                    "estudiante", "estudiante@clubpaen.com", "Maria", "Estudiante",
                    "300-222-2222", "ESTUDIANTE", "1234");
        };
    }

    private void createOrUpdateUser(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     String username,
                                     String email,
                                     String nombre,
                                     String apellido,
                                     String telefono,
                                     String rol,
                                     String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setFechaRegistro(LocalDateTime.now());
        }

        user.setEmail(email);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setTelefono(telefono);
        user.setRol(rol);
        user.setActivo(true);

        if (user.getPassword() == null || !BCRYPT_PATTERN.matcher(user.getPassword()).matches()) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }

        if (user.getFechaRegistro() == null) {
            user.setFechaRegistro(LocalDateTime.now());
        }

        userRepository.save(user);
    }
}
