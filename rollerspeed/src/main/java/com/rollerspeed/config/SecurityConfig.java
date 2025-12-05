package com.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Configuración de seguridad básica
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuramos qué rutas son públicas y cuáles necesitan login
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (todo el mundo puede entrar)
                .requestMatchers(
                    "/", 
                    "/mision", 
                    "/vision", 
                    "/valores", 
                    "/servicios", 
                    "/eventos",
                    "/registro",
                    "/confirmacion",
                    "/css/**", 
                    "/js/**", 
                    "/images/**"
                ).permitAll()
                
                // Rutas solo para administradores (más adelante)
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Cualquier otra ruta necesita estar logueado
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // Página de login (la crearemos después)
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }

    // Usuario administrador en memoria (solo para pruebas)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123")) // Cambiá esto en producción!
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(admin);
    }

    // Encoder para encriptar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
