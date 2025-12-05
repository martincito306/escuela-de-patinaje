package com.rollerspeed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador para todas las páginas institucionales
@Controller
public class InstitucionalController {

    // Página principal
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activePage", "index");
        return "index";
    }

    // Página de misión
    @GetMapping("/mision")
    public String mision(Model model) {
        model.addAttribute("activePage", "mision");
        return "mision";
    }

    // Página de visión
    @GetMapping("/vision")
    public String vision(Model model) {
        model.addAttribute("activePage", "vision");
        return "vision";
    }

    // Página de valores
    @GetMapping("/valores")
    public String valores(Model model) {
        model.addAttribute("activePage", "valores");
        return "valores";
    }

    // Página de servicios
    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("activePage", "servicios");
        return "servicios";
    }

    // Página de eventos
    @GetMapping("/eventos")
    public String eventos(Model model) {
        model.addAttribute("activePage", "eventos");
        return "eventos";
    }
}
