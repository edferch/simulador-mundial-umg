package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    
    // Algoritmo de Búsqueda: Busca jugadores que contengan el texto, ignorando mayúsculas/minúsculas
    List<Jugador> findByNombreContainingIgnoreCase(String nombre);

    // Nos trae los goleadores ordenados de mayor a menor
    List<Jugador> findTop10ByOrderByGolesAnotadosDesc();

    // Nos trae a los porteros ordenados por los que tienen MENOS goles recibidos
    List<Jugador> findTop10ByPosicionOrderByGolesRecibidosAsc(String posicion);

    List<Jugador> findByEquipo(Equipo equipo);
}
