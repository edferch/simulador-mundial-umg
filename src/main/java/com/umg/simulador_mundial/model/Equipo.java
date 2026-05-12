package com.umg.simulador_mundial.model;

public class Equipo {
    private Long id;
    private String nombreEquipo;
    private Pais pais;
    private String grupo;

    public Equipo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }
    
    // Alias de compatibilidad para evitar errores en Thymeleaf si busca .nombre
    public String getNombre() { return nombreEquipo; }
    public void setNombre(String nombre) { this.nombreEquipo = nombre; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }
    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
}