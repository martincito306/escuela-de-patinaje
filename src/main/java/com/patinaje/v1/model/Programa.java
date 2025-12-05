package com.patinaje.v1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "programas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long idPrograma;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(nullable = false, length = 20)
    private String nivel;
    
    @Column(name = "edad_minima")
    private Integer edadMinima;
    
    @Column(name = "edad_maxima")
    private Integer edadMaxima;
    
    @Column(name = "duracion_meses")
    private Integer duracionMeses;
    
    @Column(name = "costo_mensual", precision = 10, scale = 2)
    private BigDecimal costoMensual;
    
    @Column(name = "costo_matricula", precision = 10, scale = 2)
    private BigDecimal costoMatricula;
    
    @Column(name = "dias_semana", length = 50)
    private String diasSemana;
    
    @Column(length = 50)
    private String icono;
    
    @Column
    private Boolean activo = true;
    
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
