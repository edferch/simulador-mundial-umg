package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Posicion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PosicionDAO {
    @Autowired private DataSource dataSource;

    public List<Posicion> findAll() {
        List<Posicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM posiciones ORDER BY id_posicion ASC";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Posicion(rs.getLong("id_posicion"), rs.getString("descripcion")));
            }
        } catch (SQLException e) { System.err.println("Error en PosicionDAO: " + e.getMessage()); }
        return lista;
    }
}