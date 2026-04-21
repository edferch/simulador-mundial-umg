package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Encuentro;
import com.umg.simulador_mundial.model.Gol;
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
    @Autowired private EncuentroDAO encuentroDao;

    // MÉTODO AUXILIAR
    private Gol mapearGol(ResultSet rs) throws SQLException {
        Gol g = new Gol();
        g.setId(rs.getLong("id"));
        g.setMinuto(rs.getInt("minuto"));
        
        // Extraer los objetos completos usando las llaves foráneas
        g.setJugador(jugadorDao.findById(rs.getLong("jugador_id")));
        g.setEncuentro(encuentroDao.findById(rs.getLong("encuentro_id")));
        return g;
    }

    // Buscar los goles de un partido específico ordenados por minuto
    public List<Gol> findByEncuentroOrderByMinutoAsc(Encuentro encuentro) {
        List<Gol> lista = new ArrayList<>();
        String sql = "SELECT * FROM goles WHERE encuentro_id = ? ORDER BY minuto ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, encuentro.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearGol(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar los goles del partido: " + e.getMessage());
        }
        return lista;
    }

    // Insertar un nuevo gol
    public void save(Gol gol) {
        if (gol.getId() == null) {
            String sql = "INSERT INTO goles (minuto, jugador_id, encuentro_id) VALUES (?, ?, ?)";
            
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setInt(1, gol.getMinuto());
                ps.setLong(2, gol.getJugador().getId());
                ps.setLong(3, gol.getEncuentro().getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al registrar el gol: " + e.getMessage());
            }
        }
    }

    // Limpiar tabla (usado al reiniciar sorteos)
    public void deleteAll() {
        String sql = "DELETE FROM goles";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error al borrar los goles: " + e.getMessage());
        }
    }
}