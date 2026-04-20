package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JugadorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EquipoDAO equipoDao; // Usamos el DAO de equipos para jalar el equipo al que pertenece

    private RowMapper<Jugador> jugadorRowMapper = new RowMapper<Jugador>() {
        @Override
        public Jugador mapRow(ResultSet rs, int rowNum) throws SQLException {
            Jugador j = new Jugador();
            j.setId(rs.getLong("id"));
            j.setNombre(rs.getString("nombre"));
            j.setPosicion(rs.getString("posicion"));
            j.setNumeroCamiseta(rs.getInt("numero_camiseta"));
            j.setGolesAnotados(rs.getInt("goles_anotados"));
            j.setGolesRecibidos(rs.getInt("goles_recibidos"));
            // Buscamos el equipo usando la llave foránea
            j.setEquipo(equipoDao.findById(rs.getLong("equipo_id")));
            return j;
        }
    };

    public List<Jugador> findAll() {
        String sql = "SELECT * FROM jugadores ORDER BY equipo_id ASC, numero_camiseta ASC";
        return jdbcTemplate.query(sql, jugadorRowMapper);
    }

    public Jugador findById(Long id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        List<Jugador> resultados = jdbcTemplate.query(sql, jugadorRowMapper, id);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public List<Jugador> findByEquipo(Equipo equipo) {
        String sql = "SELECT * FROM jugadores WHERE equipo_id = ? ORDER BY numero_camiseta ASC";
        return jdbcTemplate.query(sql, jugadorRowMapper, equipo.getId());
    }

    public List<Jugador> findByNombreContainingIgnoreCase(String nombre) {
        String sql = "SELECT * FROM jugadores WHERE ILIKE(nombre, ?) ORDER BY nombre ASC";
        return jdbcTemplate.query(sql, jugadorRowMapper, "%" + nombre + "%");
    }

    // Para la bota de oro y guante de oro
    public List<Jugador> findTop10ByOrderByGolesAnotadosDesc() {
        String sql = "SELECT * FROM jugadores ORDER BY goles_anotados DESC LIMIT 10";
        return jdbcTemplate.query(sql, jugadorRowMapper);
    }

    public List<Jugador> findTop10ByPosicionOrderByGolesRecibidosAsc(String posicion) {
        String sql = "SELECT * FROM jugadores WHERE posicion = ? ORDER BY goles_recibidos ASC LIMIT 10";
        return jdbcTemplate.query(sql, jugadorRowMapper, posicion);
    }

    public void save(Jugador jugador) {
        if (jugador.getId() == null) {
            String sql = "INSERT INTO jugadores (nombre, posicion, numero_camiseta, goles_anotados, goles_recibidos, equipo_id) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, jugador.getNombre(), jugador.getPosicion(), jugador.getNumeroCamiseta(), 
                                jugador.getGolesAnotados(), jugador.getGolesRecibidos(), jugador.getEquipo().getId());
        } else {
            String sql = "UPDATE jugadores SET nombre = ?, posicion = ?, numero_camiseta = ?, goles_anotados = ?, goles_recibidos = ?, equipo_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, jugador.getNombre(), jugador.getPosicion(), jugador.getNumeroCamiseta(), 
                                jugador.getGolesAnotados(), jugador.getGolesRecibidos(), jugador.getEquipo().getId(), jugador.getId());
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}