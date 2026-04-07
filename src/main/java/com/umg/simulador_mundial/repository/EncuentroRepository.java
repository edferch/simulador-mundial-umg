package com.umg.simulador_mundial.repository;

import com.umg.simulador_mundial.model.Encuentro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuentroRepository extends JpaRepository<Encuentro, Long> {
    // Aquí es donde sucede la magia del CRUD automático
}