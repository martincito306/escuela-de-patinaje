package com.patinaje.v1.controller;

import com.patinaje.v1.model.ContactoMensaje;
import com.patinaje.v1.service.ContactoMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    @Autowired
    private ContactoMensajeService contactoMensajeService;

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

    // ===== API REST ENDPOINTS =====

    // GET /api/contacto - Obtener todos los mensajes
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<ContactoMensaje>> obtenerTodosAPI() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerTodos();
        return ResponseEntity.ok(mensajes);
    }

    // GET /api/contacto/no-leidos - Obtener mensajes no leídos
    @GetMapping("/api/no-leidos")
    @ResponseBody
    public ResponseEntity<List<ContactoMensaje>> obtenerNoLeidosAPI() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerNoLeidos();
        return ResponseEntity.ok(mensajes);
    }

    // GET /api/contacto/{id} - Obtener mensaje por ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<ContactoMensaje> obtenerPorIdAPI(@PathVariable Long id) {
        return contactoMensajeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/contacto - Crear nuevo mensaje de contacto
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> crearAPI(@RequestBody ContactoMensaje mensaje) {
        try {
            ContactoMensaje nuevoMensaje = contactoMensajeService.crear(mensaje);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Mensaje enviado exitosamente");
            response.put("id", nuevoMensaje.getId());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al enviar el mensaje: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // PUT /api/contacto/{id}/leer - Marcar mensaje como leído
    @PutMapping("/api/{id}/leer")
    @ResponseBody
    public ResponseEntity<ContactoMensaje> marcarComoLeidoAPI(@PathVariable Long id) {
        try {
            ContactoMensaje mensaje = contactoMensajeService.marcarComoLeido(id);
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/contacto/{id} - Eliminar mensaje
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarAPI(@PathVariable Long id) {
        contactoMensajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
