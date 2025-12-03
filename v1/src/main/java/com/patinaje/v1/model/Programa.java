package com.patinaje.v1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "programas")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private String nivel; // principiante, intermedio, avanzado, competencia

    private Integer edadMinima;
    
    private Integer edadMaxima;

    private String duracion; // ej: "1.5 horas", "2 meses"

    private String icono; // para mostrar en el frontend, ej: "fa-child"

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean activo = true;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    // Constructores
    public Programa() {
    }

    public Programa(String nombre, String descripcion, String nivel, Integer edadMinima, Integer edadMaxima, String duracion, String icono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.duracion = duracion;
        this.icono = icono;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    public Integer getEdadMaxima() {
        return edadMaxima;
    }

    public void setEdadMaxima(Integer edadMaxima) {
        this.edadMaxima = edadMaxima;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
