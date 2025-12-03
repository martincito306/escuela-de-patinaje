package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/programas")
    public String programas() {
        return "programas"; // Vista para programas
    }

    @GetMapping("/horarios")
    public String horarios() {
        return "horarios"; // Vista para horarios
    }

    @GetMapping("/galeria")
    public String galeria() {
        return "galeria"; // Vista para galería
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto"; // Vista para contacto
    }
}
