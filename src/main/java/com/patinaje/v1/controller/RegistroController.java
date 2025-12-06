package com.patinaje.v1.controller;

import com.patinaje.v1.model.User;
import com.patinaje.v1.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {

    private static final List<String> ROLES_PERMITIDOS = List.of("ESTUDIANTE", "INSTRUCTOR");

    @Autowired
    private UserService userService;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        if (!model.containsAttribute("datosFormulario")) {
            model.addAttribute("datosFormulario", Map.of());
        }
        model.addAttribute("roles", ROLES_PERMITIDOS);
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre,
                                   @RequestParam String apellido,
                                   @RequestParam String email,
                                   @RequestParam String username,
                                   @RequestParam(required = false) String telefono,
                                   @RequestParam String rol,
                                   @RequestParam String password,
                                   @RequestParam("confirmarPassword") String confirmarPassword,
                                   RedirectAttributes redirectAttributes) {
        List<String> errores = new ArrayList<>();

        if (!ROLES_PERMITIDOS.contains(rol)) {
            errores.add("Selecciona un rol válido.");
        }
        if (!password.equals(confirmarPassword)) {
            errores.add("Las contraseñas no coinciden.");
        }
        if (userService.findByUsername(username) != null) {
            errores.add("El nombre de usuario ya está en uso.");
        }
        if (userService.findByEmail(email) != null) {
            errores.add("El correo electrónico ya está registrado.");
        }

        Map<String, String> datosFormulario = Map.of(
                "nombre", nombre,
                "apellido", apellido,
                "email", email,
                "username", username,
                "telefono", telefono != null ? telefono : "",
                "rol", rol
        );

        if (!errores.isEmpty()) {
            redirectAttributes.addFlashAttribute("errores", errores);
            redirectAttributes.addFlashAttribute("datosFormulario", datosFormulario);
            return "redirect:/registro";
        }

        User nuevoUsuario = new User();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setRol(rol);
        nuevoUsuario.setPassword(password); // Se encripta en UserService
        nuevoUsuario.setActivo(true);

        userService.save(nuevoUsuario);

        redirectAttributes.addFlashAttribute("registroExito", "Tu cuenta fue creada. Ahora puedes iniciar sesión.");
        return "redirect:/login";
    }
}
