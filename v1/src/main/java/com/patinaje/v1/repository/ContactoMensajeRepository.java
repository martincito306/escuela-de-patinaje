package com.patinaje.v1.repository;

import com.patinaje.v1.model.ContactoMensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoMensajeRepository extends JpaRepository<ContactoMensaje, Long> {
    
    // Buscar mensajes no leídos
    List<ContactoMensaje> findByLeidoFalse();
    
    // Buscar por email
    List<ContactoMensaje> findByEmail(String email);
    
    // Buscar por asunto
    List<ContactoMensaje> findByAsunto(String asunto);
}
