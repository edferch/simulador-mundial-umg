package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.EventoEncuentro;
import com.umg.simulador_mundial.model.Encuentro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoEncuentroRepository extends JpaRepository<EventoEncuentro, Long> {
    // Para buscar todos los goles/tarjetas de un partido específico
    List<EventoEncuentro> findByEncuentroOrderByMinutoAsc(Encuentro encuentro);
}