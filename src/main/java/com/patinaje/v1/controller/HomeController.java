package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal del sitio
 * Acá están todas las páginas del menú
 */
@Controller
public class HomeController {

    // Cuando alguien entra a la raíz, lo mandamos a /inicio
    @GetMapping("/")
    public String root() {
        return "redirect:/inicio";
    }

    // Página principal con bienvenida y noticias
    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("activePage", "inicio");
        return "inicio";
    }

    // Página con los programas/cursos que ofrecemos
    @GetMapping("/programas")
    public String programas(Model model) {
        model.addAttribute("activePage", "programas");
        return "programas";
    }

    // Tabla con los horarios de todas las clases
    @GetMapping("/horarios")
    public String horarios(Model model) {
        model.addAttribute("activePage", "horarios");
        return "horarios";
    }

    // Galería con fotos de eventos y entrenamientos
    @GetMapping("/galeria")
    public String galeria(Model model) {
        model.addAttribute("activePage", "galeria");
        return "galeria";
    }

    // Formulario de contacto
    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("activePage", "contacto");
        return "contacto";
    }
}
