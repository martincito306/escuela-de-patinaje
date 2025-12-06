package com.patinaje.v1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        // Redirigir según el rol del usuario
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin/usuarios";
            } else if (role.equals("ROLE_INSTRUCTOR")) {
                return "redirect:/instructor/panel";
            } else if (role.equals("ROLE_ESTUDIANTE")) {
                return "redirect:/estudiante/panel";
            }
        }
        
        // Por defecto, redirigir a inicio
        return "redirect:/inicio";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}
