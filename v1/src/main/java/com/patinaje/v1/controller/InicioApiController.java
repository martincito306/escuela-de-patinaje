package com.patinaje.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class InicioApiController {

    @GetMapping("/inicio")
    public Map<String, Object> getInicio() {
        Map<String, Object> response = new HashMap<>();
        
        response.put("mensaje", "¡Bienvenido a la Escuela de Patinaje!");
        response.put("estado", "activo");
        
        // Valores Corporativos
        List<Map<String, String>> valores = new ArrayList<>();
        valores.add(Map.of("titulo", "Pasión", "descripcion", "Amamos el patinaje y transmitimos esa pasión a cada estudiante"));
        valores.add(Map.of("titulo", "Seguridad", "descripcion", "La protección y bienestar de nuestros alumnos es prioridad"));
        valores.add(Map.of("titulo", "Excelencia", "descripcion", "Instructores certificados y metodología de clase mundial"));
        response.put("valores", valores);
        
        // Misión y Visión
        Map<String, String> misionVision = new HashMap<>();
        misionVision.put("mision", "Formar patinadores integrales a través de la enseñanza de técnicas profesionales, promoviendo valores como la disciplina, el respeto y el trabajo en equipo.");
        misionVision.put("vision", "Ser la escuela de patinaje líder en la región, reconocida por la calidad de nuestros programas de formación.");
        response.put("misionVision", misionVision);
        
        // Horarios
        List<Map<String, Object>> horarios = new ArrayList<>();
        horarios.add(Map.of(
            "categoria", "Niños (5-12 años)",
            "horarios", List.of("Lunes y Miércoles: 4:00 PM - 5:30 PM", "Sábados: 9:00 AM - 11:00 AM", "Domingos: 10:00 AM - 12:00 PM")
        ));
        horarios.add(Map.of(
            "categoria", "Adolescentes (13-17 años)",
            "horarios", List.of("Martes y Jueves: 5:00 PM - 6:30 PM", "Sábados: 2:00 PM - 4:00 PM", "Domingos: 3:00 PM - 5:00 PM")
        ));
        horarios.add(Map.of(
            "categoria", "Adultos (18+ años)",
            "horarios", List.of("Lunes y Miércoles: 7:00 PM - 8:30 PM", "Viernes: 6:00 PM - 8:00 PM", "Sábados: 5:00 PM - 7:00 PM")
        ));
        response.put("horarios", horarios);
        
        // Clases
        List<Map<String, String>> clases = new ArrayList<>();
        clases.add(Map.of("nivel", "Iniciación", "descripcion", "Primeros pasos en el patinaje. Equilibrio y confianza."));
        clases.add(Map.of("nivel", "Básico", "descripcion", "Técnicas fundamentales y maniobras básicas."));
        clases.add(Map.of("nivel", "Intermedio", "descripcion", "Perfecciona tu técnica y aprende trucos."));
        clases.add(Map.of("nivel", "Avanzado", "descripcion", "Preparación para competencias profesionales."));
        response.put("clases", clases);
        
        // Información de Contacto
        Map<String, String> contacto = new HashMap<>();
        contacto.put("direccion", "Av. Principal #123, Ciudad");
        contacto.put("telefono", "+57 300 123 4567");
        contacto.put("email", "info@escueladepatinaje.com");
        contacto.put("horarioAtencion", "Lun - Dom: 9:00 AM - 8:00 PM");
        response.put("contacto", contacto);
        
        return response;
    }
}
