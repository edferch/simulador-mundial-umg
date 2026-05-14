package com.umg.simulador_mundial.model;

public class Amonestacion {
    private Partido partido;
    private Jugador jugador;
    private String colorTarjeta;
    private Integer minuto;

    public Amonestacion() {}

    public Partido getPartido() { return partido; }
    public void setPartido(Partido partido) { this.partido = partido; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
    public String getColorTarjeta() { return colorTarjeta; }
    public void setColorTarjeta(String colorTarjeta) { this.colorTarjeta = colorTarjeta; }
    public Integer getMinuto() { return minuto; }
    public void setMinuto(Integer minuto) { this.minuto = minuto; }
}