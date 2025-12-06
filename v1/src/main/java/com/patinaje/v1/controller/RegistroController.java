package com.patinaje.v1.controller;

import com.patinaje.v1.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("roles", List.of("ESTUDIANTE", "INSTRUCTOR"));
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String username,
            @RequestParam String rol,
            @RequestParam String password,
            @RequestParam String confirmarPassword,
            @RequestParam(required = false) String acudiente,
            RedirectAttributes redirectAttributes) {

        List<String> errores = new ArrayList<>();

        // Validaciones
        if (!password.equals(confirmarPassword)) {
            errores.add("Las contraseñas no coinciden");
        }

        if (registroService.usuarioExistePorUsername(username)) {
            errores.add("El nombre de usuario ya está registrado");
        }

        if (registroService.usuarioExistePorEmail(email)) {
            errores.add("El email ya está registrado");
        }

        if (!List.of("ESTUDIANTE", "INSTRUCTOR").contains(rol)) {
            errores.add("Rol inválido");
        }
        
        if ("ESTUDIANTE".equals(rol) && (acudiente == null || acudiente.trim().isEmpty())) {
            errores.add("El acudiente es requerido para estudiantes");
        }

        if (!errores.isEmpty()) {
            redirectAttributes.addFlashAttribute("errores", errores);
            redirectAttributes.addFlashAttribute("datosFormulario", new java.util.HashMap<>() {{
                put("nombre", nombre);
                put("apellido", apellido);
                put("email", email);
                put("telefono", telefono);
                put("username", username);
                put("rol", rol);
            }});
            return "redirect:/registro";
        }

        // Crear nuevo usuario usando RegistroService
        try {
            if ("ESTUDIANTE".equals(rol)) {
                registroService.registrarEstudiante(nombre, apellido, email, telefono, username, password, acudiente);
            } else if ("INSTRUCTOR".equals(rol)) {
                registroService.registrarInstructor(nombre, apellido, email, telefono, username, password);
            } else {
                redirectAttributes.addFlashAttribute("error", "Rol no válido");
                return "redirect:/registro";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "redirect:/registro";
        }

        redirectAttributes.addFlashAttribute("registroExito", "Tu cuenta fue creada exitosamente. ¡Inicia sesión!");

        return "redirect:/login";
    }
}
