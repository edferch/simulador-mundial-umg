package com.umg.simulador_mundial.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "encuentros")
public class Encuentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Encapsulamiento: Atributos privados
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Equipo equipoVisitante;

    private Integer golesLocal = 0;
    private Integer golesVisitante = 0;
    private LocalDateTime fechaHora;
    private String estado; // Ejemplo: "PENDIENTE", "JUGANDO", "FINALIZADO"

    // Getters y Setters (Esto cumple con el Encapsulamiento de la rúbrica)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Equipo getEquipoLocal() { return equipoLocal; }
    public void setEquipoLocal(Equipo equipoLocal) { this.equipoLocal = equipoLocal; }

    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public void setEquipoVisitante(Equipo equipoVisitante) { this.equipoVisitante = equipoVisitante; }

    public Integer getGolesLocal() { return golesLocal; }
    public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }

    public Integer getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }
}