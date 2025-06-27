package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Reserva;
import model.ReservaBBDD;

public class ReservaBBDDTest {

	private ReservaBBDD reservaBBDD;

    @BeforeEach
    public void setUp() throws SQLException {
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservas", "root", "");
        reservaBBDD = new ReservaBBDD(conexion);
    }

    @Test
    public void testCrearYListarReserva() throws SQLException {
        Reserva reserva = new Reserva(0, 1, 1, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0));
        reservaBBDD.crearReserva(reserva);

        List<Reserva> reservas = reservaBBDD.listarReservas();
        assertFalse(reservas.isEmpty());
    }

    @Test
    public void testEliminarReserva() throws SQLException {
        reservaBBDD.eliminarReserva(1);

        List<Reserva> reservas = reservaBBDD.listarReservas();
        boolean existe = reservas.stream().anyMatch(r -> r.getId() == 1);
        assertFalse(existe);
    }

    @Test
    public void testConflicto() throws SQLException {
        Reserva reserva = new Reserva(0, 1, 1, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0));
        boolean hayConflicto = reservaBBDD.Conflicto(reserva);

        assertFalse(hayConflicto, "No debería haber conflicto si la franja está libre");
    }
}
