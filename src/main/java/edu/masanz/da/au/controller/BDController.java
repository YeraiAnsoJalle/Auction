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

    public void obtenerArticulosPendientes() {
        try (Connection conn = conectar()) {
            String sql = "SELECT * FROM articulos WHERE validado = false";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getLong("id") + ", Título: " + rs.getString("titulo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validarArticulo(long id, boolean validado) {
        try (Connection conn = conectar()) {
            String sql = "UPDATE articulos SET validado = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, validado);
            pst.setLong(2, id);
            pst.executeUpdate();
            System.out.println("Artículo actualizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verItemsConPujas() {
        String sql = "SELECT DISTINCT i.id, i.nombre FROM items i JOIN pujas p ON i.id = p.item_id";
        System.out.println("--- Items con Pujas ---");
        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            boolean encontrado = false;
            while (rs.next()) {
                encontrado = true;
                System.out.println("ID Item: " + rs.getLong("id") + ", Nombre: " + rs.getString("nombre"));
            }
            if (!encontrado) {
                System.out.println("No se encontraron items con pujas.");
            }

        } catch (Exception e) {
            System.err.println("Error al obtener items con pujas:");
            e.printStackTrace(); 
        }
    }

    public void resetearSubastas() {
        String sql = "DELETE FROM pujas"; 
        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            int filasAfectadas = pst.executeUpdate();
            System.out.println("Subastas reseteadas. Se eliminaron " + filasAfectadas + " pujas.");

        } catch (Exception e) {
            System.err.println("Error al resetear las subastas (eliminar pujas):");
            e.printStackTrace();
        }
    }

    public void validarPuja(long pujaId, boolean esValida) {
        String sql = "UPDATE pujas SET validada = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setBoolean(1, esValida);
            pst.setLong(2, pujaId);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Puja con ID " + pujaId + " actualizada correctamente. Estado validada: " + esValida);
            } else {
                System.out.println("No se encontró ninguna puja con ID " + pujaId + " para actualizar.");
            }

        } catch (Exception e) {
            System.err.println("Error al validar la puja con ID " + pujaId + ":");
            e.printStackTrace();
        }
    }

}
