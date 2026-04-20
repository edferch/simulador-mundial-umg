package com.umg.simulador_mundial.model;

public class Equipo {

    private Long id;
    private String nombre;
    private String abreviatura;
    private String entrenador;
    private String grupo; 

    // Constructores
    public Equipo() {}

    public Equipo(String nombre, String abreviatura, String entrenador) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.entrenador = entrenador;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAbreviatura() { return abreviatura; }
    public void setAbreviatura(String abreviatura) { this.abreviatura = abreviatura; }

    public String getEntrenador() { return entrenador; }
    public void setEntrenador(String entrenador) { this.entrenador = entrenador; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }
}