package com.umg.simulador_mundial.model;

public class Gol {
    private Partido partido;
    private Jugador jugador;
    private Integer minuto;
    private String tipoGol;

    public Gol() {}

    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public Integer getMinuto() { return minuto; }
    public void setMinuto(Integer minuto) { this.minuto = minuto; }
    public String getTipoGol() { return tipoGol; }
    public void setTipoGol(String tipoGol) { this.tipoGol = tipoGol; }
}