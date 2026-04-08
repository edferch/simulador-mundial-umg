package com.umg.simulador_mundial.repository;
import com.umg.simulador_mundial.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> { }