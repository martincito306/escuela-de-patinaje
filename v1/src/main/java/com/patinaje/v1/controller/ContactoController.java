package com.patinaje.v1.controller;

import com.patinaje.v1.model.ContactoMensaje;
import com.patinaje.v1.service.ContactoMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    @Autowired
    private ContactoMensajeService contactoMensajeService;

    // GET /api/contacto - Obtener todos los mensajes
    @GetMapping
    public ResponseEntity<List<ContactoMensaje>> obtenerTodos() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerTodos();
        return ResponseEntity.ok(mensajes);
    }

    // GET /api/contacto/no-leidos - Obtener mensajes no leídos
    @GetMapping("/no-leidos")
    public ResponseEntity<List<ContactoMensaje>> obtenerNoLeidos() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerNoLeidos();
        return ResponseEntity.ok(mensajes);
    }

    // GET /api/contacto/{id} - Obtener mensaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactoMensaje> obtenerPorId(@PathVariable Long id) {
        return contactoMensajeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/contacto - Crear nuevo mensaje de contacto
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody ContactoMensaje mensaje) {
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
    @PutMapping("/{id}/leer")
    public ResponseEntity<ContactoMensaje> marcarComoLeido(@PathVariable Long id) {
        try {
            ContactoMensaje mensaje = contactoMensajeService.marcarComoLeido(id);
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/contacto/{id} - Eliminar mensaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contactoMensajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
