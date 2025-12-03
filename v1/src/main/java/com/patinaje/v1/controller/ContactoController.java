package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @GetMapping
    public String contacto(Model model) {
        model.addAttribute("titulo", "Contáctanos");
        return "contacto";
    }

    @PostMapping("/enviar")
    public String enviarMensaje(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String telefono,
            @RequestParam String mensaje,
            Model model) {
        
        // Aquí se implementaría la lógica de envío de email
        model.addAttribute("mensaje", "Mensaje enviado con éxito");
        return "redirect:/index/home?success=true";
    }
}
