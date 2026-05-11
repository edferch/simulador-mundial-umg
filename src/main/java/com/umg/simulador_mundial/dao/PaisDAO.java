package com.umg.simulador_mundial.dao;

import com.umg.simulador_mundial.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaisDAO {
    @Autowired private DataSource dataSource;

    public List<Pais> findAll() {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT * FROM paises ORDER BY nombre ASC";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Pais(rs.getLong("id_pais"), rs.getString("nombre")));
            }
        } catch (SQLException e) { System.err.println("Error en PaisDAO: " + e.getMessage()); }
        return lista;
    }
}