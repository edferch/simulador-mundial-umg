package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
}