package com.patinaje.v1.service;

import com.patinaje.v1.model.Programa;
import com.patinaje.v1.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    // Obtener todos los programas
    public List<Programa> obtenerTodos() {
        return programaRepository.findAll();
    }

    // Obtener programas activos
    public List<Programa> obtenerActivos() {
        return programaRepository.findByActivoTrue();
    }

    // Obtener programa por ID
    public Optional<Programa> obtenerPorId(Long id) {
        return programaRepository.findById(id);
    }

    // Obtener programas por nivel
    public List<Programa> obtenerPorNivel(String nivel) {
        return programaRepository.findByNivel(nivel);
    }

    // Crear nuevo programa
    public Programa crear(Programa programa) {
        return programaRepository.save(programa);
    }

    // Actualizar programa
    public Programa actualizar(Long id, Programa programaActualizado) {
        return programaRepository.findById(id)
                .map(programa -> {
                    programa.setNombre(programaActualizado.getNombre());
                    programa.setDescripcion(programaActualizado.getDescripcion());
                    programa.setNivel(programaActualizado.getNivel());
                    programa.setEdadMinima(programaActualizado.getEdadMinima());
                    programa.setEdadMaxima(programaActualizado.getEdadMaxima());
                    programa.setDuracion(programaActualizado.getDuracion());
                    programa.setIcono(programaActualizado.getIcono());
                    programa.setActivo(programaActualizado.getActivo());
                    return programaRepository.save(programa);
                })
                .orElseThrow(() -> new RuntimeException("Programa no encontrado con id: " + id));
    }

    // Eliminar programa (soft delete)
    public void eliminar(Long id) {
        programaRepository.findById(id)
                .ifPresent(programa -> {
                    programa.setActivo(false);
                    programaRepository.save(programa);
                });
    }

    // Eliminar programa permanentemente
    public void eliminarPermanente(Long id) {
        programaRepository.deleteById(id);
    }
}
