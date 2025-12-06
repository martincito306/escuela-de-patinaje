package com.patinaje.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas - accesibles sin login
                .requestMatchers("/", "/inicio", "/programas", "/horarios", "/galeria", "/contacto").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/images/**").permitAll()
                .requestMatchers("/api/**").permitAll() // APIs REST públicas
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger
                .requestMatchers("/login", "/registro").permitAll()
                
                // Rutas para ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Rutas para INSTRUCTOR
                .requestMatchers("/instructor/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                .requestMatchers("/asistencia/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                
                // Rutas para ESTUDIANTE
                .requestMatchers("/estudiante/**").hasAnyRole("ESTUDIANTE", "ADMIN")
                .requestMatchers("/mi-perfil/**").hasAnyRole("ESTUDIANTE", "INSTRUCTOR", "ADMIN")
                
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/inicio?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
