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
    @Autowired private EstadioDAO estadioDao;

    private Partido mapearPartido(ResultSet rs) throws SQLException {
        Partido p = new Partido();
        p.setId(rs.getLong("id_partido"));
        
        Timestamp ts = rs.getTimestamp("fecha_hora");
        if (ts != null) p.setFechaHora(ts.toLocalDateTime());
        
        p.setGolesLocal(rs.getObject("goles_local") != null ? rs.getInt("goles_local") : null);
        p.setGolesVisitante(rs.getObject("goles_visitante") != null ? rs.getInt("goles_visitante") : null);
        
        // Deducimos el estado en base a los goles para no depender de la base de datos
        if (rs.getObject("goles_local") != null && rs.getObject("goles_visitante") != null) {
            p.setEstado("FINALIZADO");
        } else {
            p.setEstado("PENDIENTE");
        }

        // Mapeo básico de objetos relacionados (Llaves Foráneas)
        Fase f = new Fase();
        f.setId(rs.getLong("id_fase"));
        p.setFase(f);

        long idEstadio = rs.getLong("id_estadio");
        if (!rs.wasNull()) {
            p.setEstadio(estadioDao.findById(idEstadio));
        }

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
                
                if (partido.getEstadio() != null && partido.getEstadio().getId() != null) {
                    ps.setLong(2, partido.getEstadio().getId());
                } else {
                    ps.setNull(2, Types.BIGINT);
                }
                
                if (partido.getFase() != null && partido.getFase().getId() != null) {
                    ps.setLong(3, partido.getFase().getId());
                } else {
                    ps.setNull(3, Types.BIGINT);
                }
                
                if (partido.getEquipoLocal() != null && partido.getEquipoLocal().getId() != null) {
                    ps.setLong(4, partido.getEquipoLocal().getId());
                } else {
                    ps.setNull(4, Types.BIGINT);
                }
                
                if (partido.getEquipoVisitante() != null && partido.getEquipoVisitante().getId() != null) {
                    ps.setLong(5, partido.getEquipoVisitante().getId());
                } else {
                    ps.setNull(5, Types.BIGINT);
                }
                
                
                if (partido.getGolesLocal() != null) {
                    ps.setInt(6, partido.getGolesLocal());
                } else {
                    ps.setNull(6, Types.INTEGER);
                }
                
                if (partido.getGolesVisitante() != null) {
                    ps.setInt(7, partido.getGolesVisitante());
                } else {
                    ps.setNull(7, Types.INTEGER);
                }
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
                
                if (partido.getEstadio() != null && partido.getEstadio().getId() != null) {
                    ps.setLong(2, partido.getEstadio().getId());
                } else {
                    ps.setNull(2, Types.BIGINT);
                }
                
                if (partido.getFase() != null && partido.getFase().getId() != null) {
                    ps.setLong(3, partido.getFase().getId());
                } else {
                    ps.setNull(3, Types.BIGINT);
                }
                
                if (partido.getEquipoLocal() != null && partido.getEquipoLocal().getId() != null) {
                    ps.setLong(4, partido.getEquipoLocal().getId());
                } else {
                    ps.setNull(4, Types.BIGINT);
                }
                
                if (partido.getEquipoVisitante() != null && partido.getEquipoVisitante().getId() != null) {
                    ps.setLong(5, partido.getEquipoVisitante().getId());
                } else {
                    ps.setNull(5, Types.BIGINT);
                }
                
                if (partido.getGolesLocal() != null) {
                    ps.setInt(6, partido.getGolesLocal());
                } else {
                    ps.setNull(6, Types.INTEGER);
                }
                
                if (partido.getGolesVisitante() != null) {
                    ps.setInt(7, partido.getGolesVisitante());
                } else {
                    ps.setNull(7, Types.INTEGER);
                }
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