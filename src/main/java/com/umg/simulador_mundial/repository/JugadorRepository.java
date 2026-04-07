package com.umg.simulador_mundial.repository;
import com.umg.simulador_mundial.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> { }