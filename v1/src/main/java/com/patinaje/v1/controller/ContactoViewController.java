package com.patinaje.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para procesar el formulario de contacto
 * Por ahora solo imprime en consola, después se puede conectar con email
 */
@Controller
public class ContactoViewController {

    // Cuando alguien envía el formulario, llega acá
    @PostMapping("/contacto")
    public String enviarMensaje(
            @RequestParam("nombre") String nombre,
            @RequestParam("email") String email,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam("asunto") String asunto,
            @RequestParam("mensaje") String mensaje,
            RedirectAttributes redirectAttributes) {
        
        // Por ahora solo mostramos el mensaje en la consola
        // Más adelante esto se puede conectar con email real
        System.out.println("=== NUEVO MENSAJE DE CONTACTO ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("==================================");

        // Le mostramos un mensajito de éxito al usuario
        redirectAttributes.addFlashAttribute("success", 
            "¡Listo " + nombre + "! Recibimos tu mensaje. Te respondemos pronto.");
        
        return "redirect:/contacto";
    }
}
