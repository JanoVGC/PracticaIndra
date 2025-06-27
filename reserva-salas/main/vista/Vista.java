package vista;

import controlador.Controlador;
import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Vista {
	
	
	 public static void main(String[] args) {
		 Scanner scan = new Scanner(System.in);
		 
		 // connexion a la base de datos //
		 
		 try {
			 Connection conexion = DriverManager.getConnection(
					    "jdbc:oracle:thin:@//oracle.ilerna.com:1521/XEPDB2", 
					    "DM2425_ALT_JAN", 
					    "A48055547Z"
					);
			 
			 Controlador Controlador = new Controlador(conexion);
			 
			 int puntoMenu; 
			 
		do {
			System.out.println("Este es el menú principal, selecciona una opción: ");
			System.out.println("1. Gestionar las Salas");
			System.out.println("2. Gestionar a los Trabajadores");
			System.out.println("3. Gestionar las Reservas");
			System.out.println("4. Salir del menú principal");
			
			puntoMenu = scan.nextInt();
			
		switch (puntoMenu) {
		case 1 -> gestionarSalas(Controlador);
        case 2 -> gestionarTrabajadores(Controlador);
        case 3 -> gestionarReservas(Controlador);
        case 0 -> System.out.println("Abandonando menú principal...");
        default -> System.out.println("Error 404, selecciona otra opción");
        
		}	
			
		}
		while (puntoMenu != 0);
			conexion.close();		
			 
		 } catch (SQLException e){
			System.out.println("Surgió un error en la base de datos :( ");
		 }		 
	 }
	 
	 private static void gestionarSalas (Controlador controlador) {
		 Scanner scan = new Scanner(System.in);
		 
		 int puntoMenu; 
		 
		 do {
			 System.out.println("Este es el menú para Gestionar las Salas, selecciona una opción: ");
				System.out.println("1. Crear una Sala");
				System.out.println("2. Listado de Salas");
				System.out.println("3. Actualización de una Sala");
				System.out.println("4. Eliminar una Sala");
				System.out.println("5. Salir del menú de Gestión de las Salas");
				
				puntoMenu = scan.nextInt();
				
		try {
			switch (puntoMenu) {
			case 1 -> {
				
				 scan.nextLine(); // limpiar buffer
                 System.out.print("Nombre: ");
                 String nombre = scan.nextLine();
                 
                 System.out.print("Capacidad: ");
                 int capacidad = scan.nextInt();
                 
                 scan.nextLine(); // limpiar buffer
                 System.out.print("Recursos: ");
                 String recursos = scan.nextLine();

                 Sala sala = new Sala(0, nombre, capacidad, recursos);
                 controlador.crearSala(sala);
                 System.out.println("Sala creada correctamente :D");
             }
			
			case 2 -> {
				
				List<Sala> salas = controlador.listarSalas();
                System.out.println("Salas disponibles:");
                for (Sala s : salas) {
                    System.out.println(s.getId() + " / " + s.getNombre() + " (" + s.getCapacidad() + " personas)");
                }
            }
			
			case 3 -> { 
				
				 System.out.print("Que sala deseas actualizar? Introduce el ID de la sala que quieras modificar: ");
                 int id = scan.nextInt();
                 scan.nextLine(); // limpiar buffer
                 
                 System.out.print("Cual será el nuevo nombre de la sala? ");
                 String nombre = scan.nextLine();
                 
                 System.out.print("Introduce la capacidad que deseas que ocupe la sala modficada: ");
                 int capacidad = scan.nextInt();
                 scan.nextLine(); // limpiar buffer
                 
                 System.out.print("Que nuevos recursos tendrá? ");
                 String recursos = scan.nextLine();

                 Sala sala = new Sala(id, nombre, capacidad, recursos);
                 controlador.actualizarSala(sala);
                 System.out.println("Sala actualizada correctamente.");
             }
			
			case 4 -> {
				
				System.out.print("ID de la sala a eliminar: ");
                int id = scan.nextInt();
                controlador.eliminarSala(id);
                System.out.println("Sala eliminada correctamente.");
            }
			
			case 5 -> System.out.println("Regresando al menú principal...");
			default -> System.out.println("Opción no disponible! ");
			
			}
			
		} catch (SQLException e) {
			System.out.println("Surgió un error a la hora de gestionar las salas, prueba de nuevo");			
		}		
			
		} while (puntoMenu != 0);
	 }
		 
	 
	 private static void gestionarTrabajadores(Controlador controlador) {
		 Scanner scan = new Scanner(System.in);
	 
		 
		 int puntoMenu; 
		 do {
			 System.out.println("Este es el menú para Gestionar los Trabajadores, selecciona una opción: ");
				System.out.println("1. Crear un Trabajador");
				System.out.println("2. Listado de Trabajadores");
				System.out.println("3. Actualizar un Trabajador");
				System.out.println("4. Eliminar un Trabajador");
				System.out.println("5. Salir del menú de Gestión de Trabajadores");
				
				puntoMenu = scan.nextInt();
		 
		try {
			switch (puntoMenu) {
			case 1 -> {
				scan.nextLine(); // limpiar buffer
				System.out.print("Nombre: ");
                String nombre = scan.nextLine();
                
                System.out.print("Email:  ");
                String email = scan.nextLine();
                
                System.out.print("Departamento: ");
                String departamento = scan.nextLine();
                
                
                Trabajadores t = new Trabajadores (0, nombre, email, departamento);
                controlador.crearTrabajador(t); 
                System.out.println("Trabajador creado correctamente.");	
                
				}
				
			case 2 -> {
				List<Trabajadores> trabajadores = controlador.listarTrabajadores();
				System.out.println("Listado de trabajadores: ");
				
				for (Trabajadores t : trabajadores ) {
					System.out.println(t.getId() + " / "  + t.getNombre() + " / " + t.getEmail());
				}
			}
			
			case 3 -> {
				
				System.out.println("Introduce el ID del trabajador que deseas actualizar: ");
				int id = scan.nextInt();
				scan.nextLine(); // limpiar buffer // 
				
				 System.out.print("Cual será el nuevo nombre del trabajador ");
                 String nombre = scan.nextLine();
                 
                 System.out.print("Nuevo email: ");
                 String email = scan.nextLine();
                 
                 System.out.print("Nuevo departamento: ");
                 String departamento = scan.nextLine();
                 
                 
                 Trabajadores t = new Trabajadores (id, nombre, email, departamento);
                 controlador.actualizarTrabajador(t); 
                 System.out.println("Trabajador actualizado correctamente :D");
                 
			}
			
			case 4 -> {
				System.out.println("Introduce el ID del trabajador que deseas eliminar: ");
				int id = scan.nextInt();				
				controlador.eliminarTrabajador(id); 				
				System.out.println("Trabajador eliminado correctamente :D");
				
			}
			
			case 5 -> System.out.println("Regresando al menú principal...");
			default -> System.out.println("Opción no disponible! ");				
				
			}		 
			
	 } catch (SQLException e) {
		 System.out.println("Surgió un error a la hora de gestionar los trabajadores, prueba de nuevo");
		 
	 }
		 } while (puntoMenu != 0); 	 
	 }
	 
	 
	 public static void gestionarReservas (Controlador controlador) {
		 Scanner scan = new Scanner(System.in);
	 
		 int puntoMenu; 
		 do {
			 System.out.println("Este es el menú para Gestionar las Reservas, selecciona una opción: ");
				System.out.println("1. Hacer una Reserva");
				System.out.println("2. Listado de Reservas");
				System.out.println("3. Actualizar una Reserva");
				System.out.println("4. Eliminar una Reserva");
				System.out.println("5. Salir del menú de Gestión de Reservas");
				
				puntoMenu = scan.nextInt();
				
		try {
			switch (puntoMenu) {
			
			case 1 -> {
			
			System.out.print("ID Sala: ");
            int salaId = scan.nextInt();
            
            System.out.print("ID Empleado: ");
            int empleadoId = scan.nextInt();
            scan.nextLine(); // limpiar buffer //
            
            System.out.print("Fecha (YYYY-MM-DD): ");
            String fechaInput = scan.nextLine();
            LocalDate fecha = LocalDate.parse(fechaInput);
            
            System.out.print("Hora Inicio (HH:MM): ");
            String horaInicioInput = scan.nextLine();
            LocalTime horaInicio = LocalTime.parse(horaInicioInput);
            
            System.out.print("Hora Fin (HH:MM): ");
            String horaFinInput = scan.nextLine();
            LocalTime horaFin = LocalTime.parse(horaFinInput);
            
            Reserva reserva = new Reserva(0, salaId, empleadoId, fecha, horaInicio, horaFin);
            controlador.crearReserva(reserva);
            System.out.println("Reserva creada correctamente :D");
            
			}
			
			case 2 -> {
				 List<Reserva> reservas = controlador.listarReservas();
                 System.out.println("Reservas actuales:");
                 for (Reserva r : reservas) {
                     System.out.println(r.getId() + " / " + r.getIdSala() + " / " + r.getIdTrabajador() + " / " + r.getFecha() + " | " + r.getHoraInicio() + " / " + r.getHoraFin());
                     
                 }
             }
			
			case 3 -> {
				
				System.out.print("Introduce el ID de la reserva que deseas actualizar: ");
                int id = scan.nextInt();
                
                System.out.print("ID Sala: ");
                int salaId = scan.nextInt();
                
                System.out.print("ID Empleado: ");
                int empleadoId = scan.nextInt();
                scan.nextLine(); // limpiar buffer // 
                
                System.out.print("Fecha (YYYY-MM-DD): ");
                String fechaInput = scan.nextLine();
                LocalDate fecha = LocalDate.parse(fechaInput);
                
                System.out.print("Hora Inicio (HH:MM): ");
                String horaInicioInput = scan.nextLine();
                LocalTime horaInicio = LocalTime.parse(horaInicioInput);
                
                System.out.print("Hora Fin (HH:MM): ");
                String horaFinInput = scan.nextLine();
                LocalTime horaFin = LocalTime.parse(horaFinInput);

                Reserva reserva = new Reserva(id, salaId, empleadoId, fecha, horaInicio, horaFin);
                controlador.actualizarReserva(reserva);
                System.out.println("Reserva actualizada correctamente.");
			}
			
			case 4 -> {
				System.out.println("Introduce el ID de la reserva que deseas eliminar: ");
				int id = scan.nextInt();
                controlador.eliminarReserva(id);
                System.out.println("Reserva eliminada correctamente :D");
			}
			
			case 5 -> System.out.println("Regresando al menú principal... ");
			default -> System.out.println("Opción no disponible!");
			
			}
			
		} catch (SQLException e) {
			 System.out.println("Surgió un error a la hora de gestionar las reservas, prueba de nuevo");
			}
		
		 } while (puntoMenu != 0); 
		 
	 }
}
	 
	 
