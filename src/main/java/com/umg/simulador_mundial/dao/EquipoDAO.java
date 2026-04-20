package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EquipoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate; // La herramienta de Spring para ejecutar SQL

    // Mapeador: Convierte una fila de la base de datos a un objeto Java
    private RowMapper<Equipo> equipoRowMapper = new RowMapper<Equipo>() {
        @Override
        public Equipo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Equipo e = new Equipo();
            e.setId(rs.getLong("id"));
            e.setNombre(rs.getString("nombre"));
            e.setAbreviatura(rs.getString("abreviatura"));
            e.setEntrenador(rs.getString("entrenador"));
            e.setGrupo(rs.getString("grupo"));
            return e;
        }
    };

    // SELECT ALL
    public List<Equipo> findAll() {
        String sql = "SELECT * FROM equipos ORDER BY id ASC";
        return jdbcTemplate.query(sql, equipoRowMapper);
    }

    // SELECT BY ID
    public Equipo findById(Long id) {
        String sql = "SELECT * FROM equipos WHERE id = ?";
        List<Equipo> resultados = jdbcTemplate.query(sql, equipoRowMapper, id);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // INSERT O UPDATE (El reemplazo del save mágico)
    public void save(Equipo equipo) {
        if (equipo.getId() == null) {
            // Es un equipo nuevo (INSERT)
            String sql = "INSERT INTO equipos (nombre, abreviatura, entrenador, grupo) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, equipo.getNombre(), equipo.getAbreviatura(), equipo.getEntrenador(), equipo.getGrupo());
        } else {
            // Ya existe, lo actualizamos (UPDATE)
            String sql = "UPDATE equipos SET nombre = ?, abreviatura = ?, entrenador = ?, grupo = ? WHERE id = ?";
            jdbcTemplate.update(sql, equipo.getNombre(), equipo.getAbreviatura(), equipo.getEntrenador(), equipo.getGrupo(), equipo.getId());
        }
    }

    // DELETE
    public void deleteById(Long id) {
        String sql = "DELETE FROM equipos WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}