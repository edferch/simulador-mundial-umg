package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Estadio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EstadioDAO {

    @Autowired
    private DataSource dataSource;

    public List<Estadio> findAll() {
        List<Estadio> lista = new ArrayList<>();
        String sql = "SELECT * FROM estadios ORDER BY id ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Estadio e = new Estadio();
                e.setId(rs.getLong("id"));
                e.setNombre(rs.getString("nombre"));
                e.setCiudad(rs.getString("ciudad"));
                e.setCapacidad(rs.getInt("capacidad"));
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estadios: " + e.getMessage());
        }
        return lista;
    }

    public Estadio findById(Long id) {
        Estadio estadio = null;
        String sql = "SELECT * FROM estadios WHERE id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estadio = new Estadio();
                    estadio.setId(rs.getLong("id"));
                    estadio.setNombre(rs.getString("nombre"));
                    estadio.setCiudad(rs.getString("ciudad"));
                    estadio.setCapacidad(rs.getInt("capacidad"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estadio: " + e.getMessage());
        }
        return estadio;
    }

    public void save(Estadio estadio) {
        if (estadio.getId() == null) {
            String sql = "INSERT INTO estadios (nombre, ciudad, capacidad) VALUES (?, ?, ?)";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, estadio.getNombre());
                ps.setString(2, estadio.getCiudad());
                ps.setInt(3, estadio.getCapacidad());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al insertar estadio: " + e.getMessage());
            }
        } else {
            String sql = "UPDATE estadios SET nombre = ?, ciudad = ?, capacidad = ? WHERE id = ?";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                 
                ps.setString(1, estadio.getNombre());
                ps.setString(2, estadio.getCiudad());
                ps.setInt(3, estadio.getCapacidad());
                ps.setLong(4, estadio.getId());
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar estadio: " + e.getMessage());
            }
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM estadios WHERE id = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar estadio: " + e.getMessage());
            throw new RuntimeException("No se puede eliminar el estadio, está en uso.", e);
        }
    }
}