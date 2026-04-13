package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.EventoEncuentro;
import com.umg.simulador_mundial.model.Encuentro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoEncuentroRepository extends JpaRepository<EventoEncuentro, Long> {
    List<EventoEncuentro> findByEncuentroOrderByMinutoAsc(Encuentro encuentro);
}