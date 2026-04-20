package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Encuentro;
import com.umg.simulador_mundial.model.EventoEncuentro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EventoEncuentroDAO {

    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private JugadorDAO jugadorDao;
    @Autowired private EncuentroDAO encuentroDao;

    private RowMapper<EventoEncuentro> eventoRowMapper = new RowMapper<EventoEncuentro>() {
        @Override
        public EventoEncuentro mapRow(ResultSet rs, int rowNum) throws SQLException {
            EventoEncuentro ev = new EventoEncuentro();
            ev.setId(rs.getLong("id"));
            ev.setTipo(rs.getString("tipo"));
            ev.setMinuto(rs.getInt("minuto"));
            ev.setJugador(jugadorDao.findById(rs.getLong("jugador_id")));
            ev.setEncuentro(encuentroDao.findById(rs.getLong("encuentro_id")));
            return ev;
        }
    };

    public List<EventoEncuentro> findByEncuentroOrderByMinutoAsc(Encuentro encuentro) {
        String sql = "SELECT * FROM encuentro_eventos WHERE encuentro_id = ? ORDER BY minuto ASC";
        return jdbcTemplate.query(sql, eventoRowMapper, encuentro.getId());
    }

    public void save(EventoEncuentro evento) {
        if (evento.getId() == null) {
            String sql = "INSERT INTO encuentro_eventos (tipo, minuto, jugador_id, encuentro_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, evento.getTipo(), evento.getMinuto(), evento.getJugador().getId(), evento.getEncuentro().getId());
        }
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM encuentro_eventos");
    }
}