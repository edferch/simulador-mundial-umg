package com.umg.simulador_mundial.model;

public class Jugador {
    private Long id;
    private String nombreCompleto;
    private Integer numeroCamiseta;
    private Equipo equipo;
    private Posicion posicion;
    
    // Este campo ya no está en la base de datos (PostgreSQL), 
    // lo usamos solo para transportar el conteo de goles a la página web (Thymeleaf).
    private Integer golesAnotados = 0;
    private Integer golesRecibidos = 0;

    public Jugador() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    
    // Alias de compatibilidad para evitar errores en Thymeleaf si busca .nombre
    public String getNombre() { return nombreCompleto; }
    public void setNombre(String nombre) { this.nombreCompleto = nombre; }
    
    public Integer getNumeroCamiseta() { return numeroCamiseta; }
    public void setNumeroCamiseta(Integer numeroCamiseta) { this.numeroCamiseta = numeroCamiseta; }
    
    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
    
    public Posicion getPosicion() { return posicion; }
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }
    
    public Integer getGolesAnotados() { return golesAnotados; }
    public void setGolesAnotados(Integer golesAnotados) { this.golesAnotados = golesAnotados; }
    
    public Integer getGolesRecibidos() { return golesRecibidos; }
    public void setGolesRecibidos(Integer golesRecibidos) { this.golesRecibidos = golesRecibidos; }
}