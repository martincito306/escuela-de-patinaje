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

    public List<Programa> obtenerTodos() {
        return programaRepository.findAll();
    }

    public List<Programa> obtenerActivos() {
        return programaRepository.findByActivoTrue();
    }

    public Optional<Programa> obtenerPorId(Long id) {
        return programaRepository.findById(id);
    }

    public Programa guardar(Programa programa) {
        return programaRepository.save(programa);
    }

    public void eliminar(Long id) {
        programaRepository.deleteById(id);
    }
}
