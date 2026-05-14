package com.umg.simulador_mundial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String posicion; // Ejemplo: Portero, Delantero

    private Integer numeroCamiseta;

    private Integer golesAnotados = 0;
    private Integer golesRecibidos = 0;

    // Esta es la clave: Relacionamos al jugador con un equipo
    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    // Constructores
    public Jugador() {}

    public Jugador(String nombre, String posicion, Integer numeroCamiseta, Equipo equipo) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.equipo = equipo;
    }

    // Getters y Setters (Encapsulamiento)
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