package com.umg.simulador_mundial.model;

public class PosicionGrupo implements Comparable<PosicionGrupo> {
    
    private Equipo equipo;
    private int puntos = 0;
    private int partidosJugados = 0;
    private int golesFavor = 0;
    private int golesContra = 0;
    private int diferenciaGoles = 0;

    public PosicionGrupo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void registrarPartido(int golesAnotados, int golesRecibidos) {
        this.partidosJugados++;
        this.golesFavor += golesAnotados;
        this.golesContra += golesRecibidos;
        this.diferenciaGoles = this.golesFavor - this.golesContra;

        if (golesAnotados > golesRecibidos) {
            this.puntos += 3;
        } else if (golesAnotados == golesRecibidos) {
            this.puntos += 1;
        }
    }

    @Override
    public int compareTo(PosicionGrupo otra) {
        if (this.puntos != otra.puntos) {
            return Integer.compare(otra.puntos, this.puntos);
        }
        if (this.diferenciaGoles != otra.diferenciaGoles) {
            return Integer.compare(otra.diferenciaGoles, this.diferenciaGoles);
        }
        return Integer.compare(otra.golesFavor, this.golesFavor);
    }

    public Equipo getEquipo() { return equipo; }
    public int getPuntos() { return puntos; }
    public int getPartidosJugados() { return partidosJugados; }
    public int getGolesFavor() { return golesFavor; }
    public int getGolesContra() { return golesContra; }
    public int getDiferenciaGoles() { return diferenciaGoles; }
}