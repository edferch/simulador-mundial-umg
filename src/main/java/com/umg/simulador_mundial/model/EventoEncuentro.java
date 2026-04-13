package com.umg.simulador_mundial.model;

import jakarta.persistence.*;

@Entity
@Table(name = "encuentro_eventos")
public class EventoEncuentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "GOL", "AMARILLA", "ROJA"
    private int minuto;

    // Relación con el jugador que realizó la acción
    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    // Relación con el partido donde ocurrió
    @ManyToOne
    @JoinColumn(name = "encuentro_id", nullable = false)
    private Encuentro encuentro;

    // Constructor vacío (obligatorio para JPA)
    public EventoEncuentro() {}

    // Constructor útil para crear eventos rápido
    public EventoEncuentro(String tipo, int minuto, Jugador jugador, Encuentro encuentro) {
        this.tipo = tipo;
        this.minuto = minuto;
        this.jugador = jugador;
        this.encuentro = encuentro;
    }

    // Getters y Setters (Encapsulamiento)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public Encuentro getEncuentro() { return encuentro; }
    public void setEncuentro(Encuentro encuentro) { this.encuentro = encuentro; }
}