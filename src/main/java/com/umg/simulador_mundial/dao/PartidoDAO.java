package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Partido;
import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Estadio;
import com.umg.simulador_mundial.model.Fase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartidoDAO {

    @Autowired private DataSource dataSource;
    @Autowired private EquipoDAO equipoDao;

    private Partido mapearPartido(ResultSet rs) throws SQLException {
        Partido p = new Partido();
        p.setId(rs.getLong("id_partido"));
        
        Timestamp ts = rs.getTimestamp("fecha_hora");
        if (ts != null) p.setFechaHora(ts.toLocalDateTime());
        
        p.setGolesLocal(rs.getInt("goles_local"));
        p.setGolesVisitante(rs.getInt("goles_visitante"));

        // Mapeo básico de objetos relacionados (Llaves Foráneas)
        Fase f = new Fase();
        f.setId(rs.getLong("id_fase"));
        p.setFase(f);

        Estadio est = new Estadio();
        est.setId(rs.getLong("id_estadio"));
        p.setEstadio(est);

        // Usamos EquipoDAO para traer los equipos completos
        p.setEquipoLocal(equipoDao.findById(rs.getLong("id_equipo_local")));
        p.setEquipoVisitante(equipoDao.findById(rs.getLong("id_equipo_visitante")));
        
        return p;
    }

    public List<Partido> findAll() {
        List<Partido> lista = new ArrayList<>();
        String sql = "SELECT * FROM partidos ORDER BY id_partido ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearPartido(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar partidos: " + e.getMessage());
        }
        return lista;
    }

    public Partido findById(Long id) {
        Partido partido = null;
        String sql = "SELECT * FROM partidos WHERE id_partido = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) partido = mapearPartido(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar partido: " + e.getMessage());
        }
        return partido;
    }

    public void save(Partido partido) {
        if (partido.getId() == null) {
            String sql = "INSERT INTO partidos (fecha_hora, id_estadio, id_fase, id_equipo_local, id_equipo_visitante, goles_local, goles_visitante) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                 
                ps.setTimestamp(1, partido.getFechaHora() != null ? Timestamp.valueOf(partido.getFechaHora()) : null);
                ps.setLong(2, partido.getEstadio().getId());
                ps.setLong(3, partido.getFase().getId());
                ps.setLong(4, partido.getEquipoLocal().getId());
                ps.setLong(5, partido.getEquipoVisitante().getId());
                ps.setInt(6, partido.getGolesLocal());
                ps.setInt(7, partido.getGolesVisitante());
                ps.executeUpdate();
                
                // Recuperar el ID generado para la base de datos
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        partido.setId(generatedKeys.getLong(1));
                    }
                }
            } catch (SQLException e) { System.err.println("Error al insertar partido: " + e.getMessage()); }
        } else {
            String sql = "UPDATE partidos SET fecha_hora = ?, id_estadio = ?, id_fase = ?, id_equipo_local = ?, id_equipo_visitante = ?, goles_local = ?, goles_visitante = ? WHERE id_partido = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setTimestamp(1, partido.getFechaHora() != null ? Timestamp.valueOf(partido.getFechaHora()) : null);
                ps.setLong(2, partido.getEstadio().getId());
                ps.setLong(3, partido.getFase().getId());
                ps.setLong(4, partido.getEquipoLocal().getId());
                ps.setLong(5, partido.getEquipoVisitante().getId());
                ps.setInt(6, partido.getGolesLocal());
                ps.setInt(7, partido.getGolesVisitante());
                ps.setLong(8, partido.getId());
                ps.executeUpdate();
            } catch (SQLException e) { System.err.println("Error al actualizar partido: " + e.getMessage()); }
        }
    }

    public void deleteAll() {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("DELETE FROM partidos");
        } catch (SQLException e) { System.err.println("Error al borrar todos los partidos: " + e.getMessage()); }
    }
}