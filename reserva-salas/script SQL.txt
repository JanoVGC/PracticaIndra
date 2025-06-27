CREATE TABLE trabajadores (
    id_trabajador INT PRIMARY KEY,
    nombre VARCHAR(100),
    departamento VARCHAR(100),
    estado_trabajador VARCHAR(50)
);


CREATE TABLE salas (
    id_sala INT PRIMARY KEY,
    nombre VARCHAR(100),
    capacidad INT,
    recursos VARCHAR(255),
    estado_sala VARCHAR(50)
);

CREATE TABLE reservas (
    id_reserva INT PRIMARY KEY,
    id_trabajador INT,
    id_sala INT,
    fecha DATE,
    hora_inicio TIMESTAMP,
    hora_fin TIMESTAMP,
    CONSTRAINT fk_trabajador FOREIGN KEY (id_trabajador) REFERENCES trabajadores(id_trabajador),
    CONSTRAINT fk_sala FOREIGN KEY (id_sala) REFERENCES salas(id_sala)
);