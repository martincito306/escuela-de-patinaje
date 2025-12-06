package com.patinaje.v1.service;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario con nombre, apellido, email y handle
     */
    public Usuario registrarUsuario(String firstName, String lastName, String email, String handle) {
        // Verificar si el email ya existe
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario(firstName, lastName, email, handle);
        return usuarioRepository.save(usuario);
    }

    /**
     * Guarda o actualiza un usuario
     */
    public Usuario guardarUsuario(Usuario usuario) {
        // Verificar si el email ya existe (solo si es nuevo o cambió el email)
        if (usuario.getId() == null || !usuarioRepository.findById(usuario.getId())
                .map(u -> u.getEmail().equals(usuario.getEmail()))
                .orElse(false)) {
            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                throw new RuntimeException("El email ya está registrado");
            }
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Obtiene todos los usuarios
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca un usuario por email
     */
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Busca un usuario por ID
     */
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Elimina un usuario por ID
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Verifica si existe un usuario con el email dado
     */
    public boolean existePorEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }
}
