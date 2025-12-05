package com.patinaje.v1.controller;

import com.patinaje.v1.model.ContactoMensaje;
import com.patinaje.v1.service.ContactoMensajeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Contacto", description = "API para gestión de mensajes de contacto")
public class ContactoController {

    @Autowired
    private ContactoMensajeService contactoMensajeService;

    @Operation(
        summary = "Obtener todos los mensajes",
        description = "Retorna la lista completa de mensajes de contacto"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de mensajes obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ContactoMensaje.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<ContactoMensaje>> obtenerTodos() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerTodos();
        return ResponseEntity.ok(mensajes);
    }

    @Operation(
        summary = "Obtener mensajes no leídos",
        description = "Retorna solo los mensajes que aún no han sido leídos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de mensajes no leídos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ContactoMensaje.class)
            )
        )
    })
    @GetMapping("/no-leidos")
    public ResponseEntity<List<ContactoMensaje>> obtenerNoLeidos() {
        List<ContactoMensaje> mensajes = contactoMensajeService.obtenerNoLeidos();
        return ResponseEntity.ok(mensajes);
    }

    @Operation(
        summary = "Obtener mensaje por ID",
        description = "Retorna un mensaje de contacto específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Mensaje encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ContactoMensaje.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Mensaje no encontrado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContactoMensaje> obtenerPorId(
        @Parameter(description = "ID del mensaje a buscar", required = true)
        @PathVariable Long id
    ) {
        return contactoMensajeService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear nuevo mensaje de contacto",
        description = "Registra un nuevo mensaje enviado desde el formulario de contacto"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Mensaje creado exitosamente"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error al procesar el mensaje"
        )
    })
    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(
        @Parameter(description = "Datos del mensaje de contacto", required = true)
        @RequestBody ContactoMensaje mensaje
    ) {
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

    @Operation(
        summary = "Marcar mensaje como leído",
        description = "Actualiza el estado de un mensaje a leído"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Mensaje marcado como leído",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ContactoMensaje.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Mensaje no encontrado"
        )
    })
    @PutMapping("/{id}/leer")
    public ResponseEntity<ContactoMensaje> marcarComoLeido(
        @Parameter(description = "ID del mensaje a marcar como leído", required = true)
        @PathVariable Long id
    ) {
        try {
            ContactoMensaje mensaje = contactoMensajeService.marcarComoLeido(id);
            return ResponseEntity.ok(mensaje);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Eliminar mensaje",
        description = "Elimina un mensaje de contacto permanentemente"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Mensaje eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Mensaje no encontrado"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
        @Parameter(description = "ID del mensaje a eliminar", required = true)
        @PathVariable Long id
    ) {
        contactoMensajeService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
