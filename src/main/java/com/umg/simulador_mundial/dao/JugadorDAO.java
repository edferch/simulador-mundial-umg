package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JugadorDAO {

    @Autowired private DataSource dataSource;
    @Autowired private EquipoDAO equipoDao; // Reutilizamos tu EquipoDAO

    private Jugador mapearJugador(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getLong("id_jugador"));
        j.setNombreCompleto(rs.getString("nombre_completo"));
        j.setNumeroCamiseta(rs.getInt("numero_camiseta"));
        
        Posicion pos = new Posicion();
        pos.setId(rs.getLong("id_posicion"));
        pos.setDescripcion(rs.getString("desc_posicion"));
        j.setPosicion(pos);
        
        j.setEquipo(equipoDao.findById(rs.getLong("id_equipo")));
        return j;
    }

    public List<Jugador> findAll() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT j.id_jugador, j.nombre_completo, j.numero_camiseta, j.id_equipo, p.id_posicion, p.descripcion AS desc_posicion " +
                     "FROM jugadores j JOIN posiciones p ON j.id_posicion = p.id_posicion ORDER BY j.id_equipo ASC, j.numero_camiseta ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapearJugador(rs));
        } catch (SQLException e) {
            System.err.println("Error en findAll Jugadores: " + e.getMessage());
        }
        return lista;
    }

    public Jugador findById(Long id) {
        Jugador j = null;
        String sql = "SELECT j.id_jugador, j.nombre_completo, j.numero_camiseta, j.id_equipo, p.id_posicion, p.descripcion AS desc_posicion " +
                     "FROM jugadores j JOIN posiciones p ON j.id_posicion = p.id_posicion WHERE j.id_jugador = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) j = mapearJugador(rs);
            }
        } catch (SQLException e) { System.err.println("Error en findById: " + e.getMessage()); }
        return j;
    }

    public List<Jugador> findByEquipo(Equipo equipo) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT j.id_jugador, j.nombre_completo, j.numero_camiseta, j.id_equipo, p.id_posicion, p.descripcion AS desc_posicion " +
                     "FROM jugadores j JOIN posiciones p ON j.id_posicion = p.id_posicion WHERE j.id_equipo = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, equipo.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapearJugador(rs));
            }
        } catch (SQLException e) { System.err.println("Error en findByEquipo: " + e.getMessage()); }
        return lista;
    }

    // EL CÁLCULO DEL PICHICHI (REPORTES BIG DATA)
    public List<Jugador> findTop10ByOrderByGolesAnotadosDesc() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT j.id_jugador, j.nombre_completo, j.numero_camiseta, j.id_equipo, " +
                     "p.id_posicion, p.descripcion AS desc_posicion, COUNT(g.id_jugador) AS total_goles " +
                     "FROM jugadores j " +
                     "JOIN posiciones p ON j.id_posicion = p.id_posicion " +
                     "JOIN goles g ON j.id_jugador = g.id_jugador " +
                     "GROUP BY j.id_jugador, p.id_posicion, p.descripcion " +
                     "ORDER BY total_goles DESC LIMIT 10";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Jugador j = mapearJugador(rs);
                // Aquí usamos tu campo temporal para que Thymeleaf pueda dibujarlo
                j.setGolesAnotados(rs.getInt("total_goles")); 
                lista.add(j);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar goleadores: " + e.getMessage());
        }
        return lista;
    }

    public void save(Jugador jugador) {
        if (jugador.getId() == null) {
            String sql = "INSERT INTO jugadores (nombre_completo, numero_camiseta, id_equipo, id_posicion) VALUES (?, ?, ?, ?)";
            try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, jugador.getNombreCompleto());
                ps.setInt(2, jugador.getNumeroCamiseta());
                ps.setLong(3, jugador.getEquipo().getId());
                ps.setLong(4, jugador.getPosicion().getId());
                ps.executeUpdate();
            } catch (SQLException e) { System.err.println("Error al insertar: " + e.getMessage()); }
        } else {
            String sql = "UPDATE jugadores SET nombre_completo = ?, numero_camiseta = ?, id_equipo = ?, id_posicion = ? WHERE id_jugador = ?";
            try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, jugador.getNombreCompleto());
                ps.setInt(2, jugador.getNumeroCamiseta());
                ps.setLong(3, jugador.getEquipo().getId());
                ps.setLong(4, jugador.getPosicion().getId());
                ps.setLong(5, jugador.getId());
                ps.executeUpdate();
            } catch (SQLException e) { System.err.println("Error al actualizar: " + e.getMessage()); }
        }
    }

    // 6. DELETE
    public void deleteById(Long id) {
        String sql = "DELETE FROM jugadores WHERE id_jugador = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage());
            throw new RuntimeException("No se puede eliminar el jugador.", e);
        }
    }

    // BUSCADOR POR NOMBRE (Para la barra de búsqueda web)
    public List<Jugador> findByNombreContainingIgnoreCase(String nombre) {
        List<Jugador> lista = new ArrayList<>();
        // Hacemos el JOIN habitual, pero filtramos con ILIKE por el nombre_completo
        String sql = "SELECT j.id_jugador, j.nombre_completo, j.numero_camiseta, j.id_equipo, p.id_posicion, p.descripcion AS desc_posicion " +
                     "FROM jugadores j JOIN posiciones p ON j.id_posicion = p.id_posicion " +
                     "WHERE j.nombre_completo ILIKE ? ORDER BY j.nombre_completo ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // Agregamos los % para que encuentre el texto sin importar si está al inicio, medio o final
            ps.setString(1, "%" + nombre + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar jugadores por nombre: " + e.getMessage());
        }
        return lista;
    }
}