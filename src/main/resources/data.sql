DROP TABLE IF EXISTS Multa;
DROP TABLE IF EXISTS Prestamo;
DROP TABLE IF EXISTS EstadoPrestamo;
DROP TABLE IF EXISTS EstadoLibroFisico;
DROP TABLE IF EXISTS LibroFisico;
DROP TABLE IF EXISTS LibroVirtual;
DROP TABLE IF EXISTS ImagenLibro;
DROP TABLE IF EXISTS Pago;
DROP TABLE IF EXISTS Tarjeta;
DROP TABLE IF EXISTS Persona;
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

CREATE TABLE Persona (
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
    fechaVencimiento VARCHAR(20) NOT NULL,
    entidadBancaria VARCHAR(50) NOT NULL,
    tipoTarjetaId INTEGER NOT NULL,
    personaId INTEGER NOT NULL,
    titular VARCHAR(50) NOT NULL,
    FOREIGN KEY (personaId) REFERENCES Persona(id),
    FOREIGN KEY (tipoTarjetaId) REFERENCES TipoTarjeta(id)
);

CREATE TABLE ImagenLibro (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    imagen BLOB NOT NULL
);

CREATE TABLE LibroVirtual (
    id DECIMAL(10,2) PRIMARY KEY,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    cantidad NUMERIC NOT NULL,
    autor VARCHAR(50) NOT NULL,
    multaValorDia NUMERIC NOT NULL,
    duracionPrestamo NUMERIC NOT NULL,
    imagenId INTEGER NOT NULL,
    FOREIGN KEY (imagenId) REFERENCES ImagenLibro(id)
);

CREATE TABLE LibroFisico (
    id DECIMAL(10,2) PRIMARY KEY,
    ubicacion VARCHAR(20) UNIQUE NOT NULL,
    numeroClasificacion VARCHAR(20) NOT NULL,
    libroVirtualId INTEGER NOT NULL,
    estadoLibroFisicoId INTEGER NOT NULL,
    FOREIGN KEY (libroVirtualId) REFERENCES LibroVirtual(id),
    FOREIGN KEY (estadoLibroFisicoId) REFERENCES EstadoLibroFisico(id)
);

CREATE TABLE Pago (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    montoTotal NUMERIC NOT NULL,
    fecha VARCHAR(20) NOT NULL,
    tarjetaId INTEGER NOT NULL,
    FOREIGN KEY (tarjetaId) REFERENCES Tarjeta(id)
);

CREATE TABLE Multa (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    montoPagar NUMERIC NOT NULL,
    fechaEmision VARCHAR(20) NOT NULL,
    pagoId INTEGER NOT NULL,
    FOREIGN KEY (pagoId) REFERENCES Pago(id)
);

CREATE TABLE Prestamo (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    fechaPrestamo VARCHAR(20) NOT NULL,
    fechaDevolucion VARCHAR(20) NOT NULL,
    personaId INTEGER NOT NULL,
    libroFisicoId INTEGER NOT NULL,
    estadoPrestamoId INTEGER NOT NULL,
    multaId INTEGER NOT NULL,
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
(2, 'vencido');

INSERT INTO EstadoLibroFisico (id, nombre) VALUES
(1, 'disponible'),
(2, 'prestado');

INSERT INTO Persona (cedula, nombre, contrasena, tipoPersonaId) VALUES
(1019983323, 'Sebastian Martinez Panesso', 'lavidaesbella24', 1),
(1019983324, 'Maria Rosario Castro Tijeras', 'contrasena123', 2),
(1019982313, 'Ana Cecilia de Armas Caso', 'MarylinMonroe24', 2);

