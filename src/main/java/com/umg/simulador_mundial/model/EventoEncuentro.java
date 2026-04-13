package com.umg.simulador_mundial.model;

import jakarta.persistence.*;

@Entity
@Table(name = "encuentro_eventos")
public class EventoEncuentro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "GOL", "AMARILLA"
    private int minuto;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "encuentro_id", nullable = false)
    private Encuentro encuentro;

    public EventoEncuentro() {}

    public EventoEncuentro(String tipo, int minuto, Jugador jugador, Encuentro encuentro) {
        this.tipo = tipo;
        this.minuto = minuto;
        this.jugador = jugador;
        this.encuentro = encuentro;
    }

    // Getters y Setters
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