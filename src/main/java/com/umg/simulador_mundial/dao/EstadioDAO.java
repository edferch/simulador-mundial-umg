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

    @Autowired private DataSource dataSource;

    public List<Estadio> findAll() {
        List<Estadio> lista = new ArrayList<>();
        String sql = "SELECT * FROM estadios ORDER BY id_estadio ASC";

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Estadio e = new Estadio();
                e.setId(rs.getLong("id_estadio"));
                e.setNombre(rs.getString("nombre"));
                e.setCiudad(rs.getString("ciudad"));
                lista.add(e);
            }
        } catch (SQLException e) { System.err.println("Error al listar estadios: " + e.getMessage()); }
        return lista;
    }

    public Estadio findById(Long id) {
        Estadio estadio = null;
        String sql = "SELECT * FROM estadios WHERE id_estadio = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estadio = new Estadio();
                    estadio.setId(rs.getLong("id_estadio"));
                    estadio.setNombre(rs.getString("nombre"));
                    estadio.setCiudad(rs.getString("ciudad"));
                }
            }
        } catch (SQLException e) { System.err.println("Error al buscar estadio: " + e.getMessage()); }
        return estadio;
    }

    public void save(Estadio estadio) {
        if (estadio.getId() == null) {
            String sql = "INSERT INTO estadios (nombre, ciudad) VALUES (?, ?)";
            try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, estadio.getNombre());
                ps.setString(2, estadio.getCiudad());
                ps.executeUpdate();
            } catch (SQLException e) { System.err.println("Error al insertar estadio: " + e.getMessage()); }
        } else {
            String sql = "UPDATE estadios SET nombre = ?, ciudad = ? WHERE id_estadio = ?";
            try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, estadio.getNombre());
                ps.setString(2, estadio.getCiudad());
                ps.setLong(3, estadio.getId());
                ps.executeUpdate();
            } catch (SQLException e) { System.err.println("Error al actualizar estadio: " + e.getMessage()); }
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM estadios WHERE id_estadio = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException("No se puede eliminar el estadio, está en uso.", e); }
    }
}