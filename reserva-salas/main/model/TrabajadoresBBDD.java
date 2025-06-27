package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadoresBBDD {

    private Connection conexion;

    public TrabajadoresBBDD(Connection conexion) {
        this.conexion = conexion;
    }

    public void crearEmpleado(Trabajadores trabajador) throws SQLException {
        String sql = "INSERT INTO trabajadores (nombre, email, departamento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, trabajador.getNombre());
            stmt.setString(2, trabajador.getEmail());
            stmt.setString(3, trabajador.getDepartamento());
            stmt.executeUpdate();
        }
    }

    public List<Trabajadores> listarEmpleados() throws SQLException {
        List<Trabajadores> trabajadores = new ArrayList<>();
        String sql = "SELECT * FROM trabajadores";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Trabajadores trabajador = new Trabajadores(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("departamento")
                );
                trabajadores.add(trabajador);
            }
        }
        return trabajadores;
    }

    public void actualizarEmpleado(Trabajadores trabajador) throws SQLException {
        String sql = "UPDATE trabajadores SET nombre = ?, email = ?, departamento = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, trabajador.getNombre());
            stmt.setString(2, trabajador.getEmail());
            stmt.setString(3, trabajador.getDepartamento());
            stmt.setInt(4, trabajador.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarEmpleado(int id) throws SQLException {
        String sql = "DELETE FROM trabajadores WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
