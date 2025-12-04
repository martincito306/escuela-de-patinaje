package com.patinaje.v1.controller;

import com.patinaje.v1.model.Usuario;
import com.patinaje.v1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * GET /usuarios
     * Muestra la lista de todos los usuarios registrados
     */
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios-list";
    }

    /**
     * GET /usuarios/nuevo
     * Muestra el formulario para registrar un nuevo usuario
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios-form";
    }

    /**
     * POST /usuarios
     * Guarda un nuevo usuario en la base de datos
     */
    @PostMapping
    public String guardarUsuario(@ModelAttribute Usuario usuario, 
                                RedirectAttributes redirectAttributes) {
        try {
            // Verificar si el email ya existe
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                redirectAttributes.addFlashAttribute("error", 
                    "El email ya está registrado. Por favor usa otro.");
                return "redirect:/usuarios/nuevo";
            }
            
            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("success", 
                "Usuario registrado exitosamente!");
            return "redirect:/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al registrar el usuario: " + e.getMessage());
            return "redirect:/usuarios/nuevo";
        }
    }

    /**
     * GET /usuarios/eliminar/{id}
     * Elimina un usuario (opcional)
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, 
                                 RedirectAttributes redirectAttributes) {
        try {
            usuarioRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", 
                "Usuario eliminado exitosamente!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
