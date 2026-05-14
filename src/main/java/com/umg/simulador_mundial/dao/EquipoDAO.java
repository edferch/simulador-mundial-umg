package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Equipo;
import com.umg.simulador_mundial.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipoDAO {

    @Autowired private DataSource dataSource; 

    // Mapeador auxiliar con JOIN
    private Equipo mapearEquipo(ResultSet rs) throws SQLException {
        Equipo eq = new Equipo();
        eq.setId(rs.getLong("id_equipo"));
        eq.setNombreEquipo(rs.getString("nombre_equipo"));
        eq.setGrupo(rs.getString("grupo"));

        Pais p = new Pais();
        p.setId(rs.getLong("id_pais"));
        // Asumimos que la consulta JOIN trae el nombre del pais
        p.setNombre(rs.getString("nombre_pais")); 
        eq.setPais(p);

        return eq;
    }

    public List<Equipo> findAll() {
        List<Equipo> lista = new ArrayList<>();
        // Unimos equipos con paises
        String sql = "SELECT e.id_equipo, e.nombre_equipo, e.grupo, p.id_pais, p.nombre AS nombre_pais " +
                     "FROM equipos e JOIN paises p ON e.id_pais = p.id_pais ORDER BY e.id_equipo ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearEquipo(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar equipos: " + e.getMessage());
        }
        return lista;
    }

    public Equipo findById(Long id) {
        Equipo equipo = null;
        String sql = "SELECT e.id_equipo, e.nombre_equipo, e.grupo, p.id_pais, p.nombre AS nombre_pais " +
                     "FROM equipos e JOIN paises p ON e.id_pais = p.id_pais WHERE e.id_equipo = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) equipo = mapearEquipo(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el equipo: " + e.getMessage());
        }
        return equipo;
    }

    public void save(Equipo equipo) {
        if (equipo.getId() == null) {
            String sql = "INSERT INTO equipos (nombre_equipo, id_pais, grupo) VALUES (?, ?, ?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, equipo.getNombreEquipo());
                ps.setLong(2, equipo.getPais().getId());
                ps.setString(3, equipo.getGrupo());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al insertar equipo: " + e.getMessage());
            }
        } else {
            String sql = "UPDATE equipos SET nombre_equipo = ?, id_pais = ?, grupo = ? WHERE id_equipo = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, equipo.getNombreEquipo());
                ps.setLong(2, equipo.getPais().getId());
                ps.setString(3, equipo.getGrupo());
                ps.setLong(4, equipo.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al actualizar equipo: " + e.getMessage());
            }
        }
    }

    // 5. DELETE
    public void deleteById(Long id) {
        String sql = "DELETE FROM equipos WHERE id_equipo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar equipo: " + e.getMessage());
            throw new RuntimeException("Violación de integridad: El equipo está en uso.", e);
        }
    }
}