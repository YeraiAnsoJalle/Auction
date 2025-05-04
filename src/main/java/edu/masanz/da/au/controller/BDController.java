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

    public boolean esAdmin(String username) {
        try (Connection conn = conectar()) {
            String sql = "SELECT admin FROM usuarios WHERE nombre = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getBoolean("admin");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void obtenerUsuarios() {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " - Admin: " + rs.getBoolean("admin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerUsuario(String nombre) {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM usuarios WHERE nombre = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario: " + rs.getString("nombre") + ", Admin: " + rs.getBoolean("admin"));
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearUsuario(String nombre, String password, boolean admin) {
        try (Connection conn = conectar()) {
            String sql = "INSERT INTO usuarios (nombre, password, admin) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, password);
            pst.setBoolean(3, admin);
            pst.executeUpdate();
            System.out.println("Usuario creado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarPasswordUsuario(String nombre, String nuevaPassword) {
        try (Connection conn = conectar()) {
            String sql = "UPDATE usuarios SET password = ? WHERE nombre = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nuevaPassword);
            pst.setString(2, nombre);
            pst.executeUpdate();
            System.out.println("Contraseña modificada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarRolUsuario(String nombre, String nuevoRol) {
        try (Connection conn = conectar()) {
            String sql = "UPDATE usuarios SET admin = ? WHERE nombre = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, nuevoRol.equalsIgnoreCase("admin"));
            pst.setString(2, nombre);
            pst.executeUpdate();
            System.out.println("Rol modificado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(String nombre) {
        try (Connection conn = conectar()) {
            String sql = "DELETE FROM usuarios WHERE nombre = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.executeUpdate();
            System.out.println("Usuario eliminado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
