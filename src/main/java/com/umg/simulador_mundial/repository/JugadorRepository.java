package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    
    List<Jugador> findByNombreContainingIgnoreCase(String nombre);

    List<Jugador> findTop10ByOrderByGolesAnotadosDesc();

    List<Jugador> findTop10ByPosicionOrderByGolesRecibidosAsc(String posicion);

    List<Jugador> findByEquipo(Equipo equipo);
}
