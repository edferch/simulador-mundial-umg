package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Encuentro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class EncuentroDAO {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EquipoDAO equipoDao;
    @Autowired private EstadioDAO estadioDao;

    private RowMapper<Encuentro> encuentroRowMapper = new RowMapper<Encuentro>() {
        @Override
        public Encuentro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Encuentro e = new Encuentro();
            e.setId(rs.getLong("id"));
            e.setGolesLocal(rs.getInt("goles_local"));
            e.setGolesVisitante(rs.getInt("goles_visitante"));
            
            Timestamp ts = rs.getTimestamp("fecha_hora");
            if (ts != null) e.setFechaHora(ts.toLocalDateTime());
            
            e.setEstado(rs.getString("estado"));

            // Obtenemos los objetos complejos usando los otros DAOs
            e.setEquipoLocal(equipoDao.findById(rs.getLong("local_id")));
            e.setEquipoVisitante(equipoDao.findById(rs.getLong("visitante_id")));
            
            long estadioId = rs.getLong("estadio_id");
            if (!rs.wasNull()) {
                e.setEstadio(estadioDao.findById(estadioId));
            }
            return e;
        }
    };

    public List<Encuentro> findAll() {
        String sql = "SELECT * FROM encuentros ORDER BY id ASC";
        return jdbcTemplate.query(sql, encuentroRowMapper);
    }
    
    public Encuentro findById(Long id) {
        String sql = "SELECT * FROM encuentros WHERE id = ?";
        List<Encuentro> resultados = jdbcTemplate.query(sql, encuentroRowMapper, id);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public void save(Encuentro encuentro) {
        Long estadioId = (encuentro.getEstadio() != null) ? encuentro.getEstadio().getId() : null;
        
        if (encuentro.getId() == null) {
            String sql = "INSERT INTO encuentros (local_id, visitante_id, estadio_id, goles_local, goles_visitante, fecha_hora, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, encuentro.getEquipoLocal().getId(), encuentro.getEquipoVisitante().getId(), estadioId,
                    encuentro.getGolesLocal(), encuentro.getGolesVisitante(), encuentro.getFechaHora(), encuentro.getEstado());
        } else {
            String sql = "UPDATE encuentros SET local_id = ?, visitante_id = ?, estadio_id = ?, goles_local = ?, goles_visitante = ?, fecha_hora = ?, estado = ? WHERE id = ?";
            jdbcTemplate.update(sql, encuentro.getEquipoLocal().getId(), encuentro.getEquipoVisitante().getId(), estadioId,
                    encuentro.getGolesLocal(), encuentro.getGolesVisitante(), encuentro.getFechaHora(), encuentro.getEstado(), encuentro.getId());
        }
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM encuentros");
    }
}