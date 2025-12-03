package com.patinaje.v1.repository;

import com.patinaje.v1.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    
    // Buscar horarios por nivel
    List<Horario> findByNivel(String nivel);
    
    // Buscar horarios activos
    List<Horario> findByActivoTrue();
    
    // Buscar por instructor
    List<Horario> findByInstructor(String instructor);
    
    // Buscar horarios con cupos disponibles (usando @Query)
    @org.springframework.data.jpa.repository.Query("SELECT h FROM Horario h WHERE h.cupoActual < h.cupoMaximo")
    List<Horario> findHorariosDisponibles();
}
