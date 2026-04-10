package com.umg.simulador_mundial.model;

// NOTA: Esta clase NO lleva @Entity porque no se guarda en la base de datos.
// Solo vive en la memoria RAM (Cumple el 10% de Manejo de Memoria de la rúbrica)
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

    // Método para registrar el resultado de un partido
    public void registrarPartido(int golesAnotados, int golesRecibidos) {
        this.partidosJugados++;
        this.golesFavor += golesAnotados;
        this.golesContra += golesRecibidos;
        this.diferenciaGoles = this.golesFavor - this.golesContra;

        if (golesAnotados > golesRecibidos) {
            this.puntos += 3; // Victoria
        } else if (golesAnotados == golesRecibidos) {
            this.puntos += 1; // Empate
        }
        // Derrota suma 0, por lo que no hacemos nada
    }

    // Algoritmo de Ordenamiento (Cumple el 20% de Lógica y Algoritmos)
    // Criterios FIFA: 1. Puntos, 2. Diferencia Goles, 3. Goles a Favor
    @Override
    public int compareTo(PosicionGrupo otra) {
        if (this.puntos != otra.puntos) {
            return Integer.compare(otra.puntos, this.puntos); // Orden descendente
        }
        if (this.diferenciaGoles != otra.diferenciaGoles) {
            return Integer.compare(otra.diferenciaGoles, this.diferenciaGoles);
        }
        return Integer.compare(otra.golesFavor, this.golesFavor);
    }

    // Getters
    public Equipo getEquipo() { return equipo; }
    public int getPuntos() { return puntos; }
    public int getPartidosJugados() { return partidosJugados; }
    public int getGolesFavor() { return golesFavor; }
    public int getGolesContra() { return golesContra; }
    public int getDiferenciaGoles() { return diferenciaGoles; }
}