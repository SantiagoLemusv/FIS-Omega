DROP ALL OBJECTS;
DROP TABLE IF EXISTS Multa;
DROP TABLE IF EXISTS Prestamo;
DROP TABLE IF EXISTS EstadoPrestamo;
DROP TABLE IF EXISTS EstadoLibroFisico;
DROP TABLE IF EXISTS LibroFisico;
DROP TABLE IF EXISTS LibroVirtual;
DROP TABLE IF EXISTS Pago;
DROP TABLE IF EXISTS Tarjeta;
DROP TABLE IF EXISTS PERSONA;
DROP TABLE IF EXISTS TipoPersona;
DROP TABLE IF EXISTS TipoTarjeta;

-- Creación de tablas --

CREATE TABLE EstadoLibroFisico (
    id DECIMAL(10,2) PRIMARY KEY,
    nombre VARCHAR NOT NULL UNIQUE
);

CREATE TABLE TipoPersona (
    id DECIMAL(10,2) PRIMARY KEY,
    nombre VARCHAR NOT NULL UNIQUE
);

CREATE TABLE EstadoPrestamo (
    id DECIMAL(10,2) PRIMARY KEY,
    nombre VARCHAR NOT NULL UNIQUE
);

CREATE TABLE TipoTarjeta (
    id DECIMAL(10,2) PRIMARY KEY,
    nombre VARCHAR NOT NULL UNIQUE
);

CREATE TABLE PERSONA (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    cedula NUMERIC(38, 0) UNIQUE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    tipoPersonaId INTEGER NOT NULL,
    FOREIGN KEY (tipoPersonaId) REFERENCES TipoPersona(id)
);

CREATE TABLE Tarjeta (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    numero NUMERIC UNIQUE NOT NULL,
    fechaVencimiento DATE NOT NULL,
    entidadBancaria VARCHAR(50) NOT NULL,
    tipoTarjetaId INTEGER NOT NULL,
    personaId INTEGER NOT NULL,
    titular VARCHAR(50) NOT NULL,
    FOREIGN KEY (personaId) REFERENCES Persona(id),
    FOREIGN KEY (tipoTarjetaId) REFERENCES TipoTarjeta(id)
);

CREATE TABLE LibroVirtual (
    id DECIMAL(10,2) PRIMARY KEY,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    cantidad NUMERIC NOT NULL,
    autor VARCHAR(50) NOT NULL,
    multaValorDia NUMERIC NOT NULL,
    duracionPrestamo NUMERIC NOT NULL,
    imagenLibro INTEGER NULL
);


CREATE TABLE LibroFisico (
    id DECIMAL(10,2) PRIMARY KEY,
    ubicacion VARCHAR(20) NOT NULL,
    numeroClasificacion VARCHAR(20) UNIQUE NOT NULL,
    libroVirtualId INTEGER NOT NULL,
    estadoLibroFisicoId INTEGER NOT NULL,
    FOREIGN KEY (libroVirtualId) REFERENCES LibroVirtual(id),
    FOREIGN KEY (estadoLibroFisicoId) REFERENCES EstadoLibroFisico(id)
);

CREATE TABLE Pago (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    montoTotal NUMERIC NOT NULL,
    fecha DATE NOT NULL,
    tarjetaId INTEGER NOT NULL,
    FOREIGN KEY (tarjetaId) REFERENCES Tarjeta(id)
);

CREATE TABLE Multa (
    id INTEGER PRIMARY KEY,
    montoPagar NUMERIC NOT NULL,
    fechaEmision DATE NOT NULL,
    pagoId INTEGER NULL,
    diasPasados INTEGER NOT NULL,
    FOREIGN KEY (pagoId) REFERENCES Pago(id)
);

CREATE TABLE Prestamo (
    id DECIMAL(10,2) PRIMARY KEY,
    fechaPrestamo DATE NOT NULL,
    fechaDevolucion DATE NOT NULL,
    personaId INTEGER NOT NULL,
    libroFisicoId INTEGER NOT NULL,
    estadoPrestamoId INTEGER NOT NULL,
    multaId INTEGER NULL,
    FOREIGN KEY (personaId) REFERENCES Persona(id),
    FOREIGN KEY (libroFisicoId) REFERENCES LibroFisico(id),
    FOREIGN KEY (estadoPrestamoId) REFERENCES EstadoPrestamo(id),
    FOREIGN KEY (multaId) REFERENCES Multa(id)
);

-- Inserciones de datos --

INSERT INTO TipoPersona (id, nombre) VALUES
(1, 'bibliotecario'),
(2, 'lector');

INSERT INTO TipoTarjeta (id, nombre) VALUES
(1, 'debito'),
(2, 'credito');

INSERT INTO EstadoPrestamo (id, nombre) VALUES
(1, 'activo'),
(2, 'vencido'),
(3, 'finalizado');

INSERT INTO EstadoLibroFisico (id, nombre) VALUES
(1, 'disponible'),
(2, 'prestado'),
(3, 'reservado');

CREATE TABLE LibrosReservados (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    PERSONAID BIGINT NOT NULL,
    LIBROFISICOID DECIMAL(10,2) NOT NULL,
    FECHARESERVA DATE NOT NULL,
    FOREIGN KEY (PERSONAID) REFERENCES Persona(id),
    FOREIGN KEY (LIBROFISICOID) REFERENCES LibroFisico(id)
);


INSERT INTO Persona (id, cedula, nombre, contrasena, tipoPersonaId) VALUES
(41, 1013259208, 'Juan David Ramírez Juzga', 'luna', 1),
(3, 1019983323, 'Sebastian Martinez Panesso', 'lavidaesbella24', 1),
(4, 1019983324, 'Maria Rosario Castro Tijeras', 'contrasena123', 2),
(5, 1019982313, 'Ana Cecilia de Armas Caso', 'MarilynMonroe24', 2),
(22, 1000222333, 'Mario Mendoza Zambrano', 'Fourier71', 2),
(23, 1000333444, 'Isaac Newton', 'Fourier72', 2),
(42, 597816, 'Juanita', '123456789', 2),
(21, 1000999888, 'Miguel Angel Marquez Posso', 'Fourier78', 2),
(61, 55621578, 'Juan Pedro', 'Contrasena1', 2),
(81, 1234567890, 'Pepito Fulano', 'Hola1234', 2);

INSERT INTO LibroVirtual (id, isbn, titulo, cantidad, autor, multaValorDia, duracionPrestamo) VALUES
(3, '9780672328054', 'Esto no es vida', 1, 'Daniel Samper Pizano', 2500, 15),
(4, '9780321572604', 'El Padrino', 2, 'Mario Puzo', 2500, 15),
(5, '9780470111264', 'El resplandor', 1, 'Stephen King', 2500, 15),
(6, '9780071489547', 'Rosario Tijeras', 4, 'Jorge Franco', 2500, 15),
(7, '9780321699128', 'Calculo de varias variables', 2, 'James Stewart', 5000, 7),
(8, '9780132350884', 'Inteligencia Emocional', 1, 'Daniel Golleman', 3000, 9),
(9, '9780735619678', 'Derecho Penal', 3, 'Luis Carlos Perez', 4000, 5),
(1, '1541675453', 'Lady Masacre', 3, 'Mario Mendoza', 2500, 15),
(2, '0674979857', 'Tratado de medicina interna', 2, 'Goldman Cecil', 4000, 5);



INSERT INTO LibroFisico (id, ubicacion, numeroClasificacion, libroVirtualId, estadoLibroFisicoId) VALUES
(3, 'Piso 1', 'D123 Q47', 1, 2),
(4, 'Piso 2', 'D234 Q56', 3, 2),
(5, 'Piso 3', 'D234 Q57', 4, 1),
(6, 'Piso 3', 'D234 Q58', 4, 1),
(7, 'Piso 4', 'D345 Q67', 5, 2),
(8, 'Piso 5', 'D345 Q68', 6, 2),
(9, 'Piso 5', 'D345 Q69', 6, 2),
(10, 'Piso 5', 'D456 Q78', 6, 1),
(11, 'Piso 5', 'D456 Q79', 6, 1),
(12, 'Piso 6', 'D567 Q89', 7, 1),
(13, 'Piso 6', 'D567 Q90', 7, 1),
(14, 'Piso 7', 'D567 Q91', 8, 1),
(15, 'Piso 8', 'D678 Q01', 9, 1),
(16, 'Piso 8', 'D678 Q02', 9, 3),
(17, 'Piso 8', 'D789 Q12', 9, 1),
(18, 'Piso 9', 'D789 Q13', 2, 2),
(19, 'Piso 9', 'D789 Q14', 2, 1),
(1, 'Piso 1', 'D123 Q45', 1, 2),
(2, 'Piso 1', 'D123 Q46', 1, 1);

INSERT INTO Tarjeta (numero, fechaVencimiento, entidadBancaria, tipoTarjetaId, personaId, titular) VALUES
(5245896541254789, '2025-05-09', 'Bancolombia', 1, 4, 'Maria Rosario Castro Tijeras'),
(2351452635897452, '2026-07-22', 'Bancolombia', 2, 5, 'Leonardo Dicaprio'),
(2312365478965896, '2027-04-19', 'Banco Bogotá', 1, 22, 'Mario Mendoza Zambrano'),
(1254785698563214, '2025-05-10', 'Banco Bogotá', 2, 4, 'Juanito Fulano Tijeras'),
(1234567891234567, '2028-08-19', 'BBVA', 1, 23, 'Isaac Newton');


INSERT INTO LibrosReservados(personaId,libroFisicoId,fechaReserva) VALUES
(5,5,'2024-05-11');

INSERT INTO Multa (id, montoPagar, fechaEmision, pagoId, diasPasados) VALUES
(101, 5000, '2024-05-17', NULL, 2),
(103, 2500, '2024-05-17', NULL, 1),
(102, 2500, '2024-05-17', NULL, 1),
(83, 10000, '2024-05-15', NULL, 4),
(84, 2500, '2024-05-15', NULL, 1),
(104, 25, '2024-05-17', NULL, 4),
(50, 35000, '2024-05-01', NULL, 14),
(51, 36000, '2024-05-06', NULL, 9);



INSERT INTO Prestamo (id, fechaPrestamo, fechaDevolucion, personaId, libroFisicoId, estadoPrestamoId, multaId) VALUES
(101, '2024-05-19', '2024-05-24', 21, 19, 1, NULL),
(41, '2024-05-01', '2024-05-15', 4, 3, 2, 101),
(42, '2024-05-17', '2024-06-01', 4, 8, 2, 101),
(43, '2024-04-26', '2024-05-10', 5, 1, 2, 83),
(44, '2024-05-01', '2024-05-15', 5, 9, 2, 101),
(45, '2024-04-16', '2024-04-30', 5, 4, 2, 50),
(46, '2024-04-23', '2024-05-07', 22, 7, 3, 83),
(47, '2024-05-01', '2024-05-05', 23, 18, 2, 51),
(81, '2024-05-17', '2024-05-24', 81, 5, 3, NULL),
(82, '2024-05-01', '2024-05-04', 81, 14, 2, 104);







