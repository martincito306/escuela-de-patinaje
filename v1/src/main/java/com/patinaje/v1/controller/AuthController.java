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
        if (authentication == null) {
            return "redirect:/inicio";
        }

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if ("ROLE_ADMIN".equals(role)) {
                return "redirect:/admin/usuarios";
            }
            if ("ROLE_INSTRUCTOR".equals(role)) {
                return "redirect:/instructor/panel";
            }
            if ("ROLE_ESTUDIANTE".equals(role)) {
                return "redirect:/estudiante/panel";
            }
        }

        return "redirect:/inicio";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}
