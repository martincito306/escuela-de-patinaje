package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/galeria")
public class GaleriaController {

    @GetMapping
    public String galeria(Model model) {
        model.addAttribute("titulo", "Galería de Fotos");
        return "galeria";
    }
}
