package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JugadorDAO {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EquipoDAO equipoDao; // Lo usamos para traer el objeto Equipo completo

    // ==============================================================================
    // MÉTODO AUXILIAR: Para no repetir la extracción de datos en cada consulta
    // ==============================================================================
    private Jugador mapearJugador(ResultSet rs) throws SQLException {
        Jugador j = new Jugador();
        j.setId(rs.getLong("id"));
        j.setNombre(rs.getString("nombre"));
        j.setPosicion(rs.getString("posicion"));
        j.setNumeroCamiseta(rs.getInt("numero_camiseta"));
        j.setGolesAnotados(rs.getInt("goles_anotados"));
        j.setGolesRecibidos(rs.getInt("goles_recibidos"));
        
        // Buscamos el equipo usando su llave foránea
        long equipoId = rs.getLong("equipo_id");
        j.setEquipo(equipoDao.findById(equipoId));
        
        return j;
    }

    // 1. SELECT ALL
    public List<Jugador> findAll() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores ORDER BY equipo_id ASC, numero_camiseta ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearJugador(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en findAll de Jugadores: " + e.getMessage());
        }
        return lista;
    }

    // 2. SELECT BY ID
    public Jugador findById(Long id) {
        Jugador jugador = null;
        String sql = "SELECT * FROM jugadores WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jugador = mapearJugador(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en findById de Jugadores: " + e.getMessage());
        }
        return jugador;
    }

    // 3. SELECT BY EQUIPO
    public List<Jugador> findByEquipo(Equipo equipo) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE equipo_id = ? ORDER BY numero_camiseta ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, equipo.getId());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar jugadores por equipo: " + e.getMessage());
        }
        return lista;
    }

    // 4. BUSCADOR POR NOMBRE (Para la barra de búsqueda)
    public List<Jugador> findByNombreContainingIgnoreCase(String nombre) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE nombre ILIKE ? ORDER BY nombre ASC";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // El % permite buscar coincidencias parciales
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

    // 5. TOP 10 GOLEADORES (Bota de Oro)
    public List<Jugador> findTop10ByOrderByGolesAnotadosDesc() {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores ORDER BY goles_anotados DESC LIMIT 10";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearJugador(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar goleadores: " + e.getMessage());
        }
        return lista;
    }

    // 6. TOP 10 MENOS GOLEADOS (Guante de Oro)
    public List<Jugador> findTop10ByPosicionOrderByGolesRecibidosAsc(String posicion) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM jugadores WHERE posicion = ? ORDER BY goles_recibidos ASC LIMIT 10";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setString(1, posicion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearJugador(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar porteros menos goleados: " + e.getMessage());
        }
        return lista;
    }

    // 7. INSERT Y UPDATE
    public void save(Jugador jugador) {
        if (jugador.getId() == null) {
            String sql = "INSERT INTO jugadores (nombre, posicion, numero_camiseta, goles_anotados, goles_recibidos, equipo_id) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, jugador.getNombre());
                ps.setString(2, jugador.getPosicion());
                ps.setInt(3, jugador.getNumeroCamiseta());
                ps.setInt(4, jugador.getGolesAnotados());
                ps.setInt(5, jugador.getGolesRecibidos());
                ps.setLong(6, jugador.getEquipo().getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al insertar jugador: " + e.getMessage());
            }
        } else {
            String sql = "UPDATE jugadores SET nombre = ?, posicion = ?, numero_camiseta = ?, goles_anotados = ?, goles_recibidos = ?, equipo_id = ? WHERE id = ?";
            
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, jugador.getNombre());
                ps.setString(2, jugador.getPosicion());
                ps.setInt(3, jugador.getNumeroCamiseta());
                ps.setInt(4, jugador.getGolesAnotados());
                ps.setInt(5, jugador.getGolesRecibidos());
                ps.setLong(6, jugador.getEquipo().getId());
                ps.setLong(7, jugador.getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar jugador: " + e.getMessage());
            }
        }
    }

    // 8. DELETE
    public void deleteById(Long id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage());
            throw new RuntimeException("No se puede eliminar el jugador.", e);
        }
    }
}