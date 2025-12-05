package com.patinaje.v1.controller;

import com.patinaje.v1.model.User;
import com.patinaje.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin/usuarios")
public class UserWebController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.findAll());
        model.addAttribute("titulo", "Gestión de Usuarios");
        return "admin/usuarios";
    }
    
    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new User());
        model.addAttribute("titulo", "Nuevo Usuario");
        return "admin/usuario-form";
    }
    
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> usuario = userService.findById(id);
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            model.addAttribute("titulo", "Editar Usuario");
            return "admin/usuario-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/admin/usuarios";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute User usuario, RedirectAttributes redirectAttributes) {
        try {
            if (usuario.getFechaRegistro() == null) {
                usuario.setFechaRegistro(LocalDateTime.now());
            }
            userService.save(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el usuario: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }
}
