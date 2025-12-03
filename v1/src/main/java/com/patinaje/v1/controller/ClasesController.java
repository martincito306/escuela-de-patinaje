package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clases")
public class ClasesController {

    @GetMapping
    public String clases(Model model) {
        model.addAttribute("titulo", "Nuestras Clases");
        return "clases";
    }
}
