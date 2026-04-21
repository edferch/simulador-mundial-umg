package com.umg.simulador_mundial.model;

public class Gol {
    
    private Long id;
    private int minuto;
    private Jugador jugador;
    private Encuentro encuentro;

    public Gol() {}

    public Gol(int minuto, Jugador jugador, Encuentro encuentro) {
        this.minuto = minuto;
        this.jugador = jugador;
        this.encuentro = encuentro;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public Encuentro getEncuentro() { return encuentro; }
    public void setEncuentro(Encuentro encuentro) { this.encuentro = encuentro; }
}