package com.patinaje.v1.controller;

import com.patinaje.v1.model.Programa;
import com.patinaje.v1.service.ProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
@Tag(name = "Horarios", description = "API para consultar horarios de programas")
public class HorarioController {

    @Autowired
    private ProgramaService programaService;

    @Operation(
        summary = "Obtener todos los horarios disponibles",
        description = "Retorna la lista de todos los programas activos con sus horarios"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de horarios obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Programa.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Programa>> obtenerTodos() {
        List<Programa> programas = programaService.obtenerActivos();
        return ResponseEntity.ok(programas);
    }

    @Operation(
        summary = "Obtener horario por ID",
        description = "Retorna los horarios de un programa específico"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Horario encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Programa.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Horario no encontrado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Programa> obtenerPorId(
        @Parameter(description = "ID del programa para obtener sus horarios", required = true)
        @PathVariable Long id
    ) {
        return programaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
