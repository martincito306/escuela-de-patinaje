package com.patinaje.v1.service;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(String nombre, String email, String password) {
        // Verificar si el email ya existe
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        // En producción, deberías encriptar la contraseña con BCrypt
        // String passwordEncriptada = passwordEncoder.encode(password);
        
        Usuario usuario = new Usuario(nombre, email, password);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String email, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            // En producción, usar passwordEncoder.matches(password, usuario.get().getPassword())
            return usuario;
        }
        
        return Optional.empty();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
