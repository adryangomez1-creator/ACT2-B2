CREATE DATABASE actividad2_IN5BM;
USE actividad2_IN5BM;

-- =========================
-- TABLA CLIENTES
-- =========================
CREATE TABLE Clientes (
    dpi_cliente INT PRIMARY KEY,
    nombre_cliente VARCHAR(50) NOT NULL,
    apellido_cliente VARCHAR(50) NOT NULL,
    direccion VARCHAR(100),
    estado INT NOT NULL
);

-- =========================
-- TABLA USUARIOS
-- =========================
CREATE TABLE Usuarios (
    codigo_usuario INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    rol VARCHAR(45) NOT NULL,
    estado INT NOT NULL
);

INSERT INTO Usuarios (codigo_usuario, username, password, email, rol, estado) VALUES
(1,'usuario1','1234','user1@gmail.com','ROLE_USER',1);

-- =========================
-- TABLA PRODUCTOS
-- =========================
CREATE TABLE Productos (
    codigo_producto INT PRIMARY KEY AUTO_INCREMENT,
    nombre_producto VARCHAR(60) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    estado INT NOT NULL
);

-- =========================
-- TABLA VENTAS
-- =========================
CREATE TABLE Ventas (
    codigo_venta INT PRIMARY KEY AUTO_INCREMENT,
    fecha_venta DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    estado INT NOT NULL,

    Clientes_dpi_cliente INT NOT NULL,
    Usuarios_codigo_usuario INT NOT NULL,

    CONSTRAINT fk_ventas_clientes
        FOREIGN KEY (Clientes_dpi_cliente)
        REFERENCES Clientes(dpi_cliente)
        ON DELETE CASCADE,

    CONSTRAINT fk_ventas_usuarios
        FOREIGN KEY (Usuarios_codigo_usuario)
        REFERENCES Usuarios(codigo_usuario)
        ON DELETE CASCADE
);

-- =========================
-- TABLA DETALLEVENTA
-- =========================
CREATE TABLE detalle_venta (
    codigo_detalle_venta INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,

    productos_codigo_producto INT NOT NULL,
    ventas_codigo_venta INT NOT NULL,

    CONSTRAINT fk_detalle_productos
        FOREIGN KEY (productos_codigo_producto)
        REFERENCES productos(codigo_producto)
        ON DELETE CASCADE,

    CONSTRAINT fk_detalle_ventas
        FOREIGN KEY (ventas_codigo_venta)
        REFERENCES ventas(codigo_venta)
        ON DELETE CASCADE
);



INSERT INTO Usuarios (codigo_usuario, username, password, email, rol, estado) VALUES
(1, 'admin', '1234', 'admin@gmail.com', 'ROLE_ADMIN', 1),
(2, 'user1', '1234', 'user1@gmail.com', 'ROLE_USER', 1);

INSERT INTO Clientes (dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado) VALUES
(1001, 'Juan', 'Pérez', 'Zona 1', 1),
(1002, 'María', 'Gómez', 'Zona 5', 1),
(1003, 'Carlos', 'López', 'Mixco', 1),
(1004, 'Andrea', 'Hernández', 'Villa Nueva', 1);

INSERT INTO Productos (nombre_producto, precio, stock, estado) VALUES
('Mouse Logitech', 85.00, 50, 1),
('Teclado Mecánico', 250.00, 30, 1),
('Monitor 24"', 1200.00, 15, 1),
('Audífonos Gamer', 180.00, 40, 1),
('Laptop Acer', 5500.00, 10, 1);
INSERT INTO Ventas (fecha_venta, total, estado, Clientes_dpi_cliente, Usuarios_codigo_usuario) VALUES
('2026-04-01', 350.00, 1, 1001, 1),
('2026-04-02', 1200.00, 1, 1002, 1),
('2026-04-03', 5900.00, 1, 1003, 1),
('2026-04-04', 180.00, 1, 1004, 1);


INSERT INTO detalle_venta
(cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
VALUES
(2, 85.00, 170.00, 1, 1),
(1, 250.00, 250.00, 2, 1),
(1, 1200.00, 1200.00, 3, 2),
(1, 180.00, 180.00, 4, 3),
(1, 5500.00, 5500.00, 5, 3),
(2, 85.00, 170.00, 1, 4);

SELECT * FROM usuarios;