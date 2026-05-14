package com.umg.simulador_mundial.model;

public class Alineacion {
    private Partido partido;
    private Jugador jugador;
    private Boolean esTitular;
    private Integer minutosJugados;

    public Alineacion() {}

    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public Boolean getEsTitular() { return esTitular; }
    public void setEsTitular(Boolean esTitular) { this.esTitular = esTitular; }
    public Integer getMinutosJugados() { return minutosJugados; }
    public void setMinutosJugados(Integer minutosJugados) { this.minutosJugados = minutosJugados; }
}