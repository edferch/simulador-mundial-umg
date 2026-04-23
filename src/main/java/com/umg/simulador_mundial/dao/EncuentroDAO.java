package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Encuentro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EncuentroDAO {

    @Autowired private DataSource dataSource;
    @Autowired private EquipoDAO equipoDao;
    @Autowired private EstadioDAO estadioDao;

    private Encuentro mapearEncuentro(ResultSet rs) throws SQLException {
        Encuentro e = new Encuentro();
        e.setId(rs.getLong("id"));
        e.setGolesLocal(rs.getInt("goles_local"));
        e.setGolesVisitante(rs.getInt("goles_visitante"));
        
        Timestamp ts = rs.getTimestamp("fecha_hora");
        if (ts != null) e.setFechaHora(ts.toLocalDateTime());
        
        e.setEstado(rs.getString("estado"));

        e.setEquipoLocal(equipoDao.findById(rs.getLong("local_id")));
        e.setEquipoVisitante(equipoDao.findById(rs.getLong("visitante_id")));
        
        long estadioId = rs.getLong("estadio_id");
        if (!rs.wasNull()) {
            e.setEstadio(estadioDao.findById(estadioId));
        }
        return e;
    }

    public List<Encuentro> findAll() {
        List<Encuentro> lista = new ArrayList<>();
        String sql = "SELECT * FROM encuentros ORDER BY id ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearEncuentro(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar encuentros: " + e.getMessage());
        }
        return lista;
    }
    
    public Encuentro findById(Long id) {
        Encuentro encuentro = null;
        String sql = "SELECT * FROM encuentros WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    encuentro = mapearEncuentro(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar encuentro: " + e.getMessage());
        }
        return encuentro;
    }

    public void save(Encuentro encuentro) {
        Long estadioId = (encuentro.getEstadio() != null) ? encuentro.getEstadio().getId() : null;
        
        if (encuentro.getId() == null) {
            String sql = "INSERT INTO encuentros (local_id, visitante_id, estadio_id, goles_local, goles_visitante, fecha_hora, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setLong(1, encuentro.getEquipoLocal().getId());
                ps.setLong(2, encuentro.getEquipoVisitante().getId());
                if (estadioId != null) ps.setLong(3, estadioId); else ps.setNull(3, Types.INTEGER);
                ps.setInt(4, encuentro.getGolesLocal());
                ps.setInt(5, encuentro.getGolesVisitante());
                ps.setTimestamp(6, encuentro.getFechaHora() != null ? Timestamp.valueOf(encuentro.getFechaHora()) : null);
                ps.setString(7, encuentro.getEstado());
                
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al insertar encuentro: " + e.getMessage());
            }
        } else {
            String sql = "UPDATE encuentros SET local_id = ?, visitante_id = ?, estadio_id = ?, goles_local = ?, goles_visitante = ?, fecha_hora = ?, estado = ? WHERE id = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setLong(1, encuentro.getEquipoLocal().getId());
                ps.setLong(2, encuentro.getEquipoVisitante().getId());
                if (estadioId != null) ps.setLong(3, estadioId); else ps.setNull(3, Types.INTEGER);
                ps.setInt(4, encuentro.getGolesLocal());
                ps.setInt(5, encuentro.getGolesVisitante());
                ps.setTimestamp(6, encuentro.getFechaHora() != null ? Timestamp.valueOf(encuentro.getFechaHora()) : null);
                ps.setString(7, encuentro.getEstado());
                ps.setLong(8, encuentro.getId());
                
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar encuentro: " + e.getMessage());
            }
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM encuentros";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error al borrar todos los encuentros: " + e.getMessage());
        }
    }
}