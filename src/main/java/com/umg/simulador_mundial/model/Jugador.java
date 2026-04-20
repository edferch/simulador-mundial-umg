package com.umg.simulador_mundial.model;

public class Jugador {

    private Long id;
    private String nombre;
    private String posicion; 
    private Integer numeroCamiseta;
    private Integer golesAnotados = 0;
    private Integer golesRecibidos = 0;
    private Equipo equipo; // Relación simple como objeto

    // Constructores
    public Jugador() {}

    public Jugador(String nombre, String posicion, Integer numeroCamiseta, Equipo equipo) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public Integer getNumeroCamiseta() { return numeroCamiseta; }
    public void setNumeroCamiseta(Integer numeroCamiseta) { this.numeroCamiseta = numeroCamiseta; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    public Integer getGolesAnotados() { return golesAnotados != null ? golesAnotados : 0; }
    public void setGolesAnotados(Integer golesAnotados) { this.golesAnotados = golesAnotados; }

    public Integer getGolesRecibidos() { return golesRecibidos != null ? golesRecibidos : 0; }
    public void setGolesRecibidos(Integer golesRecibidos) { this.golesRecibidos = golesRecibidos; }
}