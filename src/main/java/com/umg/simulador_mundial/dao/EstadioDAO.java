package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Estadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstadioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Estadio> estadioRowMapper = new RowMapper<Estadio>() {
        @Override
        public Estadio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estadio e = new Estadio();
            e.setId(rs.getLong("id"));
            e.setNombre(rs.getString("nombre"));
            e.setCiudad(rs.getString("ciudad"));
            e.setCapacidad(rs.getInt("capacidad"));
            return e;
        }
    };

    public List<Estadio> findAll() {
        String sql = "SELECT * FROM estadios ORDER BY id ASC";
        return jdbcTemplate.query(sql, estadioRowMapper);
    }

    public Estadio findById(Long id) {
        String sql = "SELECT * FROM estadios WHERE id = ?";
        List<Estadio> resultados = jdbcTemplate.query(sql, estadioRowMapper, id);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public void save(Estadio estadio) {
        if (estadio.getId() == null) {
            String sql = "INSERT INTO estadios (nombre, ciudad, capacidad) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, estadio.getNombre(), estadio.getCiudad(), estadio.getCapacidad());
        } else {
            String sql = "UPDATE estadios SET nombre = ?, ciudad = ?, capacidad = ? WHERE id = ?";
            jdbcTemplate.update(sql, estadio.getNombre(), estadio.getCiudad(), estadio.getCapacidad(), estadio.getId());
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM estadios WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}