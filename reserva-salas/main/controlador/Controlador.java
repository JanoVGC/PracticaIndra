package controlador;

import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Controlador {
	
	private SalaBBDD salaBBDD;
	private TrabajadoresBBDD trabajadoresBBDD;
	private ReservaBBDD reservaBBDD;
	
	 public Controlador(Connection conexion) {
	        this.salaBBDD = new SalaBBDD(conexion);
	        this.trabajadoresBBDD = new TrabajadoresBBDD(conexion);
	        this.reservaBBDD = new ReservaBBDD(conexion);
	    }
	 
	 // CRUD completo (Create, Read, Update, Delete) //
	 
	 // Sala //
	 
	 public void crearSala(Sala sala) throws SQLException {
	        salaBBDD.crearSala(sala);
	    }
	 
	 public List<Sala> listarSalas() throws SQLException {
	        return salaBBDD.listarSalas();
	    }
	 
	 public void actualizarSala(Sala sala) throws SQLException {
	        salaBBDD.actualizarSala(sala);
	    }

	    public void eliminarSala(int id) throws SQLException {
	        salaBBDD.eliminarSala(id);
	    }
	    
	    
	    
	   // Trabajadores //
	    
	    public void crearTrabajador(Trabajadores t) throws SQLException {
	        trabajadoresBBDD.crearEmpleado(t);
	    }

	    public List<Trabajadores> listarTrabajadores() throws SQLException {
	        return trabajadoresBBDD.listarEmpleados();
	    }

	    public void actualizarTrabajador(Trabajadores t) throws SQLException {
	        trabajadoresBBDD.actualizarEmpleado(t);
	    }

	    public void eliminarTrabajador(int id) throws SQLException {
	        trabajadoresBBDD.eliminarEmpleado(id);
	    }
	    
	    
	   
	    // Reserva //
	    
	    public void crearReserva(Reserva reserva) throws SQLException {
	        reservaBBDD.crearReserva(reserva);
	    }

	    public List<Reserva> listarReservas() throws SQLException {
	        return reservaBBDD.listarReservas();
	    }
	    
	    public void actualizarReserva(Reserva Reserva) {
	        reservaBBDD.actualizarReserva(Reserva);
	    }


	    public void eliminarReserva(int id) throws SQLException {
	        reservaBBDD.eliminarReserva(id);
	    }



}
