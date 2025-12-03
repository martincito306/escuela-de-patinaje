package com.patinaje.v1.controller;

import com.patinaje.v1.model.Programa;
import com.patinaje.v1.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
@CrossOrigin(origins = "*")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    // GET /api/programas - Obtener todos los programas activos
    @GetMapping
    public ResponseEntity<List<Programa>> obtenerTodos() {
        List<Programa> programas = programaService.obtenerActivos();
        return ResponseEntity.ok(programas);
    }

    // GET /api/programas/{id} - Obtener programa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Programa> obtenerPorId(@PathVariable Long id) {
        return programaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/programas/nivel/{nivel} - Obtener programas por nivel
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Programa>> obtenerPorNivel(@PathVariable String nivel) {
        List<Programa> programas = programaService.obtenerPorNivel(nivel);
        return ResponseEntity.ok(programas);
    }

    // POST /api/programas - Crear nuevo programa
    @PostMapping
    public ResponseEntity<Programa> crear(@RequestBody Programa programa) {
        Programa nuevoPrograma = programaService.crear(programa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPrograma);
    }

    // PUT /api/programas/{id} - Actualizar programa
    @PutMapping("/{id}")
    public ResponseEntity<Programa> actualizar(@PathVariable Long id, @RequestBody Programa programa) {
        try {
            Programa programaActualizado = programaService.actualizar(id, programa);
            return ResponseEntity.ok(programaActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/programas/{id} - Eliminar programa (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        programaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
