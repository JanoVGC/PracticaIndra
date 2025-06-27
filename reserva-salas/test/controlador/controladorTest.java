package controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.Reserva;
import model.Sala;
import model.Trabajadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class controladorTest {

    private Controlador controlador;

    @BeforeEach
    public void setUp() throws SQLException {
        // Cambia la URL, usuario y contraseña por los de tu entorno
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas_salas", "root", "1234");
        controlador = new Controlador(conexion);
    }

    @Test
    public void testCrearSala() throws SQLException {
        Sala sala = new Sala(0, "Sala Test", 15, "Proyector y Pizarra");
        controlador.crearSala(sala);

        List<Sala> salas = controlador.listarSalas();
        assertTrue(salas.stream().anyMatch(s -> s.getNombre().equals("Sala Test")));
    }

    @Test
    public void testCrearTrabajador() throws SQLException {
        Trabajadores trabajador = new Trabajadores(0, "Test Empleado", "test@empresa.com", "Recursos Humanos");
        controlador.crearTrabajador(trabajador);

        List<Trabajadores> trabajadores = controlador.listarTrabajadores();
        assertTrue(trabajadores.stream().anyMatch(t -> t.getNombre().equals("Test Empleado")));
    }

    @Test
    public void testCrearReserva() throws SQLException {
        // Asegúrate de que exista una sala con ID 1 y un trabajador con ID 1
        Reserva reserva = new Reserva(0, 1, 1, LocalDate.now().plusDays(1), LocalTime.of(9, 0), LocalTime.of(10, 0));
        controlador.crearReserva(reserva);

        List<Reserva> reservas = controlador.listarReservas();
        assertTrue(reservas.stream().anyMatch(r -> r.getHoraInicio().equals(LocalTime.of(9, 0))));
    }

    @Test
    public void testActualizarSala() throws SQLException {
        Sala sala = new Sala(0, "Sala Actualizada", 20, "Televisión");
        controlador.crearSala(sala);

        List<Sala> salas = controlador.listarSalas();
        Sala salaInsertada = salas.stream().filter(s -> s.getNombre().equals("Sala Actualizada")).findFirst().orElse(null);

        assertNotNull(salaInsertada);

        salaInsertada.setCapacidad(25);
        controlador.actualizarSala(salaInsertada);

        List<Sala> salasActualizadas = controlador.listarSalas();
        Sala salaModificada = salasActualizadas.stream().filter(s -> s.getId() == salaInsertada.getId()).findFirst().orElse(null);

        assertNotNull(salaModificada);
        assertEquals(25, salaModificada.getCapacidad());
    }

    @Test
    public void testEliminarReserva() throws SQLException {
        Reserva reserva = new Reserva(0, 1, 1, LocalDate.now().plusDays(2), LocalTime.of(12, 0), LocalTime.of(13, 0));
        controlador.crearReserva(reserva);

        List<Reserva> reservas = controlador.listarReservas();
        Reserva reservaCreada = reservas.stream().filter(r -> r.getHoraInicio().equals(LocalTime.of(12, 0))).findFirst().orElse(null);

        assertNotNull(reservaCreada);

        controlador.eliminarReserva(reservaCreada.getId());

        List<Reserva> reservasActuales = controlador.listarReservas();
        assertFalse(reservasActuales.stream().anyMatch(r -> r.getId() == reservaCreada.getId()));
    }
}
