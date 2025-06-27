package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Trabajadores;
import model.TrabajadoresBBDD;

public class TrabajadoresBBDDTest {

	private TrabajadoresBBDD trabajadoresBBDD;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas", "root", "");
        trabajadoresBBDD = new TrabajadoresBBDD(conexion);
    }

    @Test
    public void testCrearYListarTrabajador() throws SQLException {
        Trabajadores t = new Trabajadores(0, "Nuevo Trabajador", "nuevo@empresa.com", "IT");
        trabajadoresBBDD.crearEmpleado(t);

        List<Trabajadores> trabajadores = trabajadoresBBDD.listarEmpleados();
        assertFalse(trabajadores.isEmpty());
    }

    @Test
    public void testActualizarTrabajador() throws SQLException {
        Trabajadores t = new Trabajadores(1, "Trabajador Actualizado", "actualizado@empresa.com", "Marketing");
        trabajadoresBBDD.actualizarEmpleado(t);

        List<Trabajadores> trabajadores = trabajadoresBBDD.listarEmpleados();
        boolean existe = trabajadores.stream().anyMatch(e -> e.getNombre().equals("Trabajador Actualizado"));
        assertTrue(existe);
    }

    @Test
    public void testEliminarTrabajador() throws SQLException {
        trabajadoresBBDD.eliminarEmpleado(2);

        List<Trabajadores> trabajadores = trabajadoresBBDD.listarEmpleados();
        boolean existe = trabajadores.stream().anyMatch(e -> e.getId() == 2);
        assertFalse(existe);
    }
}
