package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horarios")
public class HorariosController {

    @GetMapping
    public String horarios(Model model) {
        // Aquí se pueden agregar datos dinámicos al modelo
        model.addAttribute("titulo", "Horarios de Clases");
        return "horarios";
    }
}
