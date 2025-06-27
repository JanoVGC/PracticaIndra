package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Sala;
import model.SalaBBDD;

public class SalaBBDDTest {

	 private SalaBBDD salaBBDD;

	    @BeforeEach
	    public void setUp() throws SQLException {
	        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas", "root", "");
	        salaBBDD = new SalaBBDD(conexion);
	    }

	    @Test
	    public void testCrearYListarSala() throws SQLException {
	        Sala sala = new Sala(0, "Sala Test", 5, "TV, Pizarra");
	        salaBBDD.crearSala(sala);

	        List<Sala> salas = salaBBDD.listarSalas();
	        assertFalse(salas.isEmpty(), "La lista no debería estar vacía");
	    }

	    @Test
	    public void testActualizarSala() throws SQLException {
	        Sala sala = new Sala(1, "Sala Actualizada", 10, "TV, Pizarra, Proyector");
	        salaBBDD.actualizarSala(sala);

	        List<Sala> salas = salaBBDD.listarSalas();
	        boolean encontrada = salas.stream().anyMatch(s -> s.getNombre().equals("Sala Actualizada"));
	        assertTrue(encontrada, "La sala actualizada debería existir");
	    }

	    @Test
	    public void testEliminarSala() throws SQLException {
	        salaBBDD.eliminarSala(2);

	        List<Sala> salas = salaBBDD.listarSalas();
	        boolean existe = salas.stream().anyMatch(s -> s.getId() == 2);
	        assertFalse(existe, "La sala eliminada no debería existir");
	    }
	}
