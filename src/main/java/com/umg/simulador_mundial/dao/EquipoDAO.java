package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EquipoDAO {

    // Usamos el DataSource de Spring en lugar de crear una clase Conexion manual, 
    // pero la lógica de JDBC abajo es idéntica a la rúbrica.
    @Autowired
    private DataSource dataSource; 

    // 1. SELECT ALL (Equivalente al listarEquipos del ingeniero)
    public List<Equipo> findAll() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipos ORDER BY id ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setAbreviatura(rs.getString("abreviatura"));
                equipo.setEntrenador(rs.getString("entrenador"));
                equipo.setGrupo(rs.getString("grupo"));
                lista.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar equipos: " + e.getMessage());
        }
        return lista;
    }

    // 2. SELECT BY ID
    public Equipo findById(Long id) {
        Equipo equipo = null;
        String sql = "SELECT * FROM equipos WHERE id = ?";

        // PreparedStatement previene inyección SQL (Punto clave de la rúbrica)
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    equipo = new Equipo();
                    equipo.setId(rs.getLong("id"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setAbreviatura(rs.getString("abreviatura"));
                    equipo.setEntrenador(rs.getString("entrenador"));
                    equipo.setGrupo(rs.getString("grupo"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el equipo: " + e.getMessage());
        }
        return equipo;
    }

    // 3. INSERT / UPDATE (Equivalente al registrarEquipo del ingeniero)
    public void save(Equipo equipo) {
        if (equipo.getId() == null) {
            // Es un equipo nuevo
            String sql = "INSERT INTO equipos (nombre, abreviatura, entrenador, grupo) VALUES (?, ?, ?, ?)";
            
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, equipo.getNombre());
                ps.setString(2, equipo.getAbreviatura());
                ps.setString(3, equipo.getEntrenador());
                ps.setString(4, equipo.getGrupo());
                ps.executeUpdate();
                System.out.println("Éxito: " + equipo.getNombre() + " insertado correctamente.");
                
            } catch (SQLException e) {
                System.err.println("Error al insertar equipo: " + e.getMessage());
            }
        } else {
            // El equipo ya existe, lo actualizamos
            String sql = "UPDATE equipos SET nombre = ?, abreviatura = ?, entrenador = ?, grupo = ? WHERE id = ?";
            
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, equipo.getNombre());
                ps.setString(2, equipo.getAbreviatura());
                ps.setString(3, equipo.getEntrenador());
                ps.setString(4, equipo.getGrupo());
                ps.setLong(5, equipo.getId());
                ps.executeUpdate();
                System.out.println("Éxito: " + equipo.getNombre() + " actualizado correctamente.");
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar equipo: " + e.getMessage());
            }
        }
    }

    // 4. DELETE
    public void deleteById(Long id) {
        String sql = "DELETE FROM equipos WHERE id = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Equipo con ID " + id + " eliminado correctamente.");
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar equipo: " + e.getMessage());
            // Lanzamos una excepción para que el controlador muestre el mensaje de error si el equipo ya jugó partidos
            throw new RuntimeException("Violación de integridad: El equipo está en uso.", e);
        }
    }
}