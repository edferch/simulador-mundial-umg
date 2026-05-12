package com.umg.simulador_mundial.model;
import java.time.LocalDateTime;

public class Partido {
    private Long id;
    private LocalDateTime fechaHora;
    private Estadio estadio;
    private Fase fase;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Integer golesLocal = null;
    private Integer golesVisitante = null;
    private String estado = "PENDIENTE";

    public Partido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public Estadio getEstadio() { return estadio; }
    public void setEstadio(Estadio estadio) { this.estadio = estadio; }
    public Fase getFase() { return fase; }
    public void setFase(Fase fase) { this.fase = fase; }
    public Equipo getEquipoLocal() { return equipoLocal; }
    public void setEquipoLocal(Equipo equipoLocal) { this.equipoLocal = equipoLocal; }
    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public void setEquipoVisitante(Equipo equipoVisitante) { this.equipoVisitante = equipoVisitante; }
    public Integer getGolesLocal() { return golesLocal; }
    public void setGolesLocal(Integer golesLocal) { this.golesLocal = golesLocal; }
    public Integer getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(Integer golesVisitante) { this.golesVisitante = golesVisitante; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}