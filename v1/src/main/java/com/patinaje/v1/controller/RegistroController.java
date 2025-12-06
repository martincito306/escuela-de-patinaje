package com.patinaje.v1.controller;

import com.patinaje.v1.model.User;
import com.patinaje.v1.service.UserService;
import com.patinaje.v1.repository.UserRepository;
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
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
            RedirectAttributes redirectAttributes) {

        List<String> errores = new ArrayList<>();

        // Validaciones
        if (!password.equals(confirmarPassword)) {
            errores.add("Las contraseñas no coinciden");
        }

        if (userRepository.findByUsername(username) != null) {
            errores.add("El nombre de usuario ya está registrado");
        }

        if (userRepository.findByEmail(email) != null) {
            errores.add("El email ya está registrado");
        }

        if (!List.of("ESTUDIANTE", "INSTRUCTOR").contains(rol)) {
            errores.add("Rol inválido");
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

        // Crear nuevo usuario
        User nuevoUsuario = new User();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setRol(rol);
        nuevoUsuario.setActivo(true);

        userService.save(nuevoUsuario);

        redirectAttributes.addFlashAttribute("registroExito", "Tu cuenta fue creada exitosamente. ¡Inicia sesión!");

        return "redirect:/login";
    }
}
