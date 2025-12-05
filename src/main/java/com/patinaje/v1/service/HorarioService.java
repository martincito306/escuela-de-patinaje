package com.patinaje.v1.service;

import com.patinaje.v1.model.Horario;
import com.patinaje.v1.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Obtener todos los horarios
    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    // Obtener horarios activos
    public List<Horario> obtenerActivos() {
        return horarioRepository.findByActivoTrue();
    }

    // Obtener horario por ID
    public Optional<Horario> obtenerPorId(Long id) {
        return horarioRepository.findById(id);
    }

    // Obtener horarios por nivel
    public List<Horario> obtenerPorNivel(String nivel) {
        return horarioRepository.findByNivel(nivel);
    }

    // Obtener horarios por instructor
    public List<Horario> obtenerPorInstructor(String instructor) {
        return horarioRepository.findByInstructor(instructor);
    }

    // Obtener horarios con cupos disponibles
    public List<Horario> obtenerConCuposDisponibles() {
        return horarioRepository.findHorariosDisponibles();
    }

    // Crear nuevo horario
    public Horario crear(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Actualizar horario
    public Horario actualizar(Long id, Horario horarioActualizado) {
        return horarioRepository.findById(id)
                .map(horario -> {
                    horario.setDias(horarioActualizado.getDias());
                    horario.setHoraInicio(horarioActualizado.getHoraInicio());
                    horario.setHoraFin(horarioActualizado.getHoraFin());
                    horario.setNivel(horarioActualizado.getNivel());
                    horario.setInstructor(horarioActualizado.getInstructor());
                    horario.setCupoMaximo(horarioActualizado.getCupoMaximo());
                    horario.setActivo(horarioActualizado.getActivo());
                    return horarioRepository.save(horario);
                })
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con id: " + id));
    }

    // Incrementar cupo actual (inscripción)
    public Horario inscribirAlumno(Long id) {
        return horarioRepository.findById(id)
                .map(horario -> {
                    if (horario.getCupoActual() < horario.getCupoMaximo()) {
                        horario.setCupoActual(horario.getCupoActual() + 1);
                        return horarioRepository.save(horario);
                    } else {
                        throw new RuntimeException("No hay cupos disponibles");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con id: " + id));
    }

    // Eliminar horario (soft delete)
    public void eliminar(Long id) {
        horarioRepository.findById(id)
                .ifPresent(horario -> {
                    horario.setActivo(false);
                    horarioRepository.save(horario);
                });
    }

    // Eliminar horario permanentemente
    public void eliminarPermanente(Long id) {
        horarioRepository.deleteById(id);
    }
    
    // Métodos adicionales para el controlador web
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }
    
    public Optional<Horario> findById(Long id) {
        return horarioRepository.findById(id);
    }
    
    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }
    
    public void deleteById(Long id) {
        horarioRepository.deleteById(id);
    }
}
