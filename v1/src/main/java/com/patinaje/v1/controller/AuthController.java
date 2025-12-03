package com.patinaje.v1.controller;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> datos) {
        try {
            String nombre = datos.get("nombre");
            String email = datos.get("email");
            String password = datos.get("password");

            // Validaciones básicas
            if (nombre == null || nombre.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "El nombre es obligatorio"));
            }
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "El email es obligatorio"));
            }
            if (password == null || password.length() < 6) {
                return ResponseEntity.badRequest().body(Map.of("message", "La contraseña debe tener al menos 6 caracteres"));
            }

            Usuario usuario = usuarioService.registrarUsuario(nombre, email, password);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("usuario", Map.of(
                "id", usuario.getId(),
                "nombre", usuario.getNombre(),
                "email", usuario.getEmail()
            ));

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error al registrar usuario"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        try {
            String email = datos.get("email");
            String password = datos.get("password");

            // Validaciones básicas
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "El email es obligatorio"));
            }
            if (password == null || password.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "La contraseña es obligatoria"));
            }

            Optional<Usuario> usuario = usuarioService.login(email, password);

            if (usuario.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login exitoso");
                response.put("usuario", Map.of(
                    "id", usuario.get().getId(),
                    "nombre", usuario.get().getNombre(),
                    "email", usuario.get().getEmail()
                ));
                // En producción, aquí generarías un JWT token
                response.put("token", "fake-jwt-token-" + usuario.get().getId());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("message", "Email o contraseña incorrectos"));
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", "Error al iniciar sesión"));
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        // Este endpoint es solo para verificar que se están guardando
        // En producción, esto debería estar protegido
        return ResponseEntity.ok(Map.of("message", "Use /api/inicio para ver información pública"));
    }
}
