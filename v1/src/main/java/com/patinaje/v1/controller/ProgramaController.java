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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
@CrossOrigin(origins = "*")
@Tag(name = "Programas", description = "API para gestión de programas de patinaje")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @Operation(
        summary = "Obtener todos los programas activos",
        description = "Retorna una lista de todos los programas de patinaje que están activos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de programas obtenida exitosamente",
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
        summary = "Obtener programa por ID",
        description = "Retorna un programa específico según su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Programa encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Programa.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Programa no encontrado"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Programa> obtenerPorId(
        @Parameter(description = "ID del programa a buscar", required = true)
        @PathVariable Long id
    ) {
        return programaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear nuevo programa",
        description = "Crea un nuevo programa de patinaje"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Programa creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Programa.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos"
        )
    })
    @PostMapping
    public ResponseEntity<Programa> crear(
        @Parameter(description = "Datos del programa a crear", required = true)
        @RequestBody Programa programa
    ) {
        Programa nuevoPrograma = programaService.guardar(programa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrograma);
    }

    @Operation(
        summary = "Eliminar programa",
        description = "Elimina un programa de patinaje por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Programa eliminado exitosamente"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Programa no encontrado"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
        @Parameter(description = "ID del programa a eliminar", required = true)
        @PathVariable Long id
    ) {
        programaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
