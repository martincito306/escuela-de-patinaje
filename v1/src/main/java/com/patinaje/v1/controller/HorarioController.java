package com.patinaje.v1.controller;

import com.patinaje.v1.model.Horario;
import com.patinaje.v1.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // GET /api/horarios - Obtener todos los horarios activos
    @GetMapping
    public ResponseEntity<List<Horario>> obtenerTodos() {
        List<Horario> horarios = horarioService.obtenerActivos();
        return ResponseEntity.ok(horarios);
    }

    // GET /api/horarios/{id} - Obtener horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerPorId(@PathVariable Long id) {
        return horarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/horarios/nivel/{nivel} - Obtener horarios por nivel
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Horario>> obtenerPorNivel(@PathVariable String nivel) {
        List<Horario> horarios = horarioService.obtenerPorNivel(nivel);
        return ResponseEntity.ok(horarios);
    }

    // GET /api/horarios/instructor/{instructor} - Obtener horarios por instructor
    @GetMapping("/instructor/{instructor}")
    public ResponseEntity<List<Horario>> obtenerPorInstructor(@PathVariable String instructor) {
        List<Horario> horarios = horarioService.obtenerPorInstructor(instructor);
        return ResponseEntity.ok(horarios);
    }

    // GET /api/horarios/disponibles - Obtener horarios con cupos disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Horario>> obtenerConCuposDisponibles() {
        List<Horario> horarios = horarioService.obtenerConCuposDisponibles();
        return ResponseEntity.ok(horarios);
    }

    // POST /api/horarios - Crear nuevo horario
    @PostMapping
    public ResponseEntity<Horario> crear(@RequestBody Horario horario) {
        Horario nuevoHorario = horarioService.crear(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHorario);
    }

    // PUT /api/horarios/{id} - Actualizar horario
    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        try {
            Horario horarioActualizado = horarioService.actualizar(id, horario);
            return ResponseEntity.ok(horarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/horarios/{id}/inscribir - Inscribir alumno a un horario
    @PostMapping("/{id}/inscribir")
    public ResponseEntity<Horario> inscribirAlumno(@PathVariable Long id) {
        try {
            Horario horario = horarioService.inscribirAlumno(id);
            return ResponseEntity.ok(horario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/horarios/{id} - Eliminar horario (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
