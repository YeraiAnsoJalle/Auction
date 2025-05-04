package edu.masanz.da.au.controller;

import java.sql.*;

public class BDController {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ut10ejemplos";
    private static final String USER = "root";
    private static final String PASS = "root";

    private Connection conectar() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, USER, PASS);
    }

    public boolean autenticar(String username, String password) {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
