package com.patinaje.v1.service;

import com.patinaje.v1.model.ContactoMensaje;
import com.patinaje.v1.repository.ContactoMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoMensajeService {

    @Autowired
    private ContactoMensajeRepository contactoMensajeRepository;

    // Obtener todos los mensajes
    public List<ContactoMensaje> obtenerTodos() {
        return contactoMensajeRepository.findAll();
    }

    // Obtener mensajes no leídos
    public List<ContactoMensaje> obtenerNoLeidos() {
        return contactoMensajeRepository.findByLeidoFalse();
    }

    // Obtener mensaje por ID
    public Optional<ContactoMensaje> obtenerPorId(Long id) {
        return contactoMensajeRepository.findById(id);
    }

    // Obtener mensajes por email
    public List<ContactoMensaje> obtenerPorEmail(String email) {
        return contactoMensajeRepository.findByEmail(email);
    }

    // Obtener mensajes por asunto
    public List<ContactoMensaje> obtenerPorAsunto(String asunto) {
        return contactoMensajeRepository.findByAsunto(asunto);
    }

    // Crear nuevo mensaje de contacto
    public ContactoMensaje crear(ContactoMensaje mensaje) {
        return contactoMensajeRepository.save(mensaje);
    }

    // Marcar mensaje como leído
    public ContactoMensaje marcarComoLeido(Long id) {
        return contactoMensajeRepository.findById(id)
                .map(mensaje -> {
                    mensaje.setLeido(true);
                    mensaje.setFechaLectura(LocalDateTime.now());
                    return contactoMensajeRepository.save(mensaje);
                })
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con id: " + id));
    }

    // Eliminar mensaje
    public void eliminar(Long id) {
        contactoMensajeRepository.deleteById(id);
    }
}
