package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleDashboardController {

    @GetMapping("/instructor/panel")
    public String instructorPanel(Model model) {
        model.addAttribute("activePage", "instructor-panel");
        model.addAttribute("tituloPagina", "Panel de Instructor");
        return "instructor/panel";
    }

    @GetMapping("/instructor/mis-clases")
    public String instructorClases(Model model) {
        model.addAttribute("activePage", "instructor-clases");
        model.addAttribute("tituloPagina", "Mis clases");
        return "instructor/mis-clases";
    }

    @GetMapping("/asistencia")
    public String asistencia(Model model) {
        model.addAttribute("activePage", "asistencia");
        model.addAttribute("tituloPagina", "Control de asistencia");
        return "instructor/asistencia";
    }

    @GetMapping("/estudiante/panel")
    public String estudiantePanel(Model model) {
        model.addAttribute("activePage", "estudiante-panel");
        model.addAttribute("tituloPagina", "Panel de estudiante");
        return "estudiante/panel";
    }

    @GetMapping("/estudiante/mis-horarios")
    public String estudianteHorarios(Model model) {
        model.addAttribute("activePage", "estudiante-horarios");
        model.addAttribute("tituloPagina", "Mis horarios");
        return "estudiante/mis-horarios";
    }

    @GetMapping("/mi-perfil")
    public String perfil(Model model) {
        model.addAttribute("activePage", "perfil");
        model.addAttribute("tituloPagina", "Mi perfil");
        return "perfil/mi-perfil";
    }
}
