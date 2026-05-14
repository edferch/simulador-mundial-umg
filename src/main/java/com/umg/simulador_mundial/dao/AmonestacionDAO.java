package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Amonestacion;
import com.umg.simulador_mundial.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AmonestacionDAO {

    @Autowired private DataSource dataSource;
    @Autowired private JugadorDAO jugadorDao;
    @Autowired private PartidoDAO partidoDao;

    public void save(Amonestacion amonestacion) {
        // En llaves compuestas solo hacemos INSERT, no UPDATE (porque si cambia, es un evento distinto)
        String sql = "INSERT INTO amonestaciones (id_partido, id_jugador, color_tarjeta, minuto) VALUES (?, ?, ?, ?)";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, amonestacion.getPartido().getId());
            ps.setLong(2, amonestacion.getJugador().getId());
            ps.setString(3, amonestacion.getColorTarjeta());
            ps.setInt(4, amonestacion.getMinuto());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al registrar tarjeta: " + e.getMessage());
        }
    }

    // Para obtener las tarjetas de un partido específico (Fair Play)
    public List<Amonestacion> findByPartido(Partido partido) {
        List<Amonestacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM amonestaciones WHERE id_partido = ? ORDER BY minuto ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, partido.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Amonestacion a = new Amonestacion();
                    a.setPartido(partido);
                    a.setJugador(jugadorDao.findById(rs.getLong("id_jugador")));
                    a.setColorTarjeta(rs.getString("color_tarjeta"));
                    a.setMinuto(rs.getInt("minuto"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tarjetas del partido: " + e.getMessage());
        }
        return lista;
    }

    public void deleteAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM amonestaciones");
        } catch (SQLException e) { System.err.println("Error al borrar las amonestaciones: " + e.getMessage()); }
    }
}