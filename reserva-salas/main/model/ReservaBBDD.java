package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaBBDD {
	
	private Connection conexion;

    public ReservaBBDD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean Conflicto(Reserva reserva) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservas WHERE id_sala = ? AND fecha = ? " +
                     "AND ((hora_inicio < ? AND hora_fin > ?) OR (hora_inicio < ? AND hora_fin > ?))";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        	stmt.setDate(2, java.sql.Date.valueOf(reserva.getFecha()));
        	stmt.setTime(3, java.sql.Time.valueOf(reserva.getHoraFin()));
        	stmt.setTime(4, java.sql.Time.valueOf(reserva.getHoraInicio()));
        	stmt.setTime(5, java.sql.Time.valueOf(reserva.getHoraFin()));
        	stmt.setTime(6, java.sql.Time.valueOf(reserva.getHoraInicio()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public void crearReserva(Reserva reserva) throws SQLException {
        if (Conflicto(reserva)) {
            throw new SQLException("Conflicto de horario: la sala ya está reservada en ese horario.");
        }

        String sql = "INSERT INTO reservas (id_sala, id_trabajador, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        	 stmt.setInt(1, reserva.getIdSala());
             stmt.setInt(2, reserva.getIdTrabajador());
             stmt.setDate(3, java.sql.Date.valueOf(reserva.getFecha())); // Conversión necesaria
             stmt.setTime(4, java.sql.Time.valueOf(reserva.getHoraInicio())); // Conversión necesaria
             stmt.setTime(5, java.sql.Time.valueOf(reserva.getHoraFin())); // Conversión necesaria
             stmt.executeUpdate();
        }
    }
    
    public List<Reserva> listarReservas() throws SQLException {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reserva reserva = new Reserva(
                		 rs.getInt("id"),
                		    rs.getInt("id_sala"),
                		    rs.getInt("id_trabajador"),
                		    rs.getDate("fecha").toLocalDate(), // Conversión
                		    rs.getTime("hora_inicio").toLocalTime(), // Conversión
                		    rs.getTime("hora_fin").toLocalTime() // Conversión
                );
                reservas.add(reserva);
            }
        }
        return reservas;
    }
    
    public void actualizarReserva (Reserva Reserva) {
    	try {
    	 if (Conflicto(Reserva)) {
    		 throw new SQLException("La sala en ese horario se encuentra ya reservada :( ");
    	 }
    	 
    	 String sql = "UPDATE reservas SET id_sala = ?, id_trabajador = ?, fecha = ?, hora_inicio = ?, hora_fin = ? WHERE id = ?";
    	 
    	 try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
    	        stmt.setInt(1, Reserva.getIdSala());
    	        stmt.setInt(2, Reserva.getIdTrabajador());
    	        stmt.setDate(3, java.sql.Date.valueOf(Reserva.getFecha()));
    	        stmt.setTime(4, java.sql.Time.valueOf(Reserva.getHoraInicio()));
    	        stmt.setTime(5, java.sql.Time.valueOf(Reserva.getHoraFin()));
    	        stmt.setInt(6, Reserva.getId());  
    	        stmt.executeUpdate();
    	    }
    	 } catch (SQLException e) { 
    		 System.out.println("Se ha producido un error a la hora de actualizar la reserva :( ");
    	 }
    	 
    }
    
    
    public void eliminarReserva(int id) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}

