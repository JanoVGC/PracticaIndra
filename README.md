# Gestor de Reservas de Salas de Reuniones en una Empresa

## Descripción General

Este proyecto es una aplicación Java que permite gestionar un sistema de reservas para salas de reuniones en una empresa. Se trata de un sistema CRUD completo que facilita la creación, lectura, actualización y eliminación de registros para salas, empleados y reservas.

El objetivo principal es evitar conflictos de reserva, asegurando que una sala no pueda estar reservada por más de una persona en el mismo horario. El proyecto ha sido desarrollado siguiendo el patrón MVC (Modelo-Vista-Controlador), utilizando Maven para la gestión del proyecto y MySQL como sistema gestor de base de datos.

Además, se han implementado pruebas unitarias con JUnit 5 para garantizar la calidad del código, y el proceso de desarrollo ha sido planificado y documentado bajo la metodología ágil Scrum, con sprints.


## Requisitos Funcionales

- Modificación y listado de salas de reuniones (nombre, capacidad, recursos disponibles).
- Gestión de empleados (nombre, email, departamento).
- Reserva de salas para una fecha y franja horaria concreta (empleado, sala, fecha, hora de inicio y fin).
- Control de conflictos para evitar reservas dobles en el mismo horario.


## Requisitos Técnicos

- Lenguaje: Java 11 o superior.
- Gestión del proyecto con Maven.
- Base de datos MySQL, con script SQL para creación del esquema.
- Implementación con JDBC para conexión con MySQL.
- Pruebas unitarias con JUnit 5.
- Modelo Entidad-Relación incluido en la documentación.


## Estructura del Proyecto

- Código fuente organizado según el patrón MVC.
- Carpeta de documentación que contiene el documento Word con la planificación Scrum, historias de usuario, y reflexiones.
- Script SQL para creación de tablas y datos iniciales.
- Pruebas unitarias en la carpeta de test.
- README.md con esta información.
