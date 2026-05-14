package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Gol;
import com.umg.simulador_mundial.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GolDAO {

    @Autowired private DataSource dataSource;
    @Autowired private JugadorDAO jugadorDao;

    public void save(Gol gol) {
        // Con llaves compuestas, hacemos INSERT directamente
        String sql = "INSERT INTO goles (id_partido, id_jugador, minuto, tipo_gol) VALUES (?, ?, ?, ?)";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, gol.getPartido().getId());
            ps.setLong(2, gol.getJugador().getId());
            ps.setInt(3, gol.getMinuto());
            ps.setString(4, gol.getTipoGol());
            ps.executeUpdate();
            
        } catch (SQLException e) { System.err.println("Error al registrar el gol: " + e.getMessage()); }
    }

    public List<Gol> findByPartido(Partido partido) {
        List<Gol> lista = new ArrayList<>();
        String sql = "SELECT * FROM goles WHERE id_partido = ? ORDER BY minuto ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, partido.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Gol g = new Gol();
                    g.setPartido(partido);
                    g.setJugador(jugadorDao.findById(rs.getLong("id_jugador")));
                    g.setMinuto(rs.getInt("minuto"));
                    g.setTipoGol(rs.getString("tipo_gol"));
                    lista.add(g);
                }
            }
        } catch (SQLException e) { System.err.println("Error al buscar goles del partido: " + e.getMessage()); }
        return lista;
    }

    public void deleteAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM goles");
        } catch (SQLException e) { System.err.println("Error al borrar los goles: " + e.getMessage()); }
    }
}