package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaBBDD {
	private Connection conexion;

	public SalaBBDD (Connection conexion) { // publica para que el constructor sea visible y actue en el codigo // 
		this.conexion = conexion;
	}
	
	public void crearSala (Sala Sala) throws SQLException {
		  String sql = "INSERT INTO salas (nombre, capacidad, recursos) VALUES (?, ?, ?)";
		  
		  try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	            stmt.setString(1, Sala.getNombre());
	            stmt.setInt(2, Sala.getCapacidad());
	            stmt.setString(3, Sala.getRecursos());
	            stmt.executeUpdate();
	        }   
			}
	
	public List<Sala> listarSalas() throws SQLException {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM salas";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
        	
            while (rs.next()) {
                Sala sala = new Sala(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad"),
                    rs.getString("recursos")
                );
                salas.add(sala);
            }
        }
        return salas;
    }
	
	  public void actualizarSala(Sala sala) throws SQLException {
	        String sql = "UPDATE salas SET nombre = ?, capacidad = ?, recursos = ? WHERE id = ?";
	        
	        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	            stmt.setString(1, sala.getNombre());
	            stmt.setInt(2, sala.getCapacidad());
	            stmt.setString(3, sala.getRecursos());
	            stmt.setInt(4, sala.getId());
	            stmt.executeUpdate();
	        }
	    }
	  
	  public void eliminarSala(int id) throws SQLException {
	        String sql = "DELETE FROM salas WHERE id = ?";
	        
	        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }
	    }
}
