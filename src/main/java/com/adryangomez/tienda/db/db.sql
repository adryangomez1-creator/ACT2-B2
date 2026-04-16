create database tienda_db_in5bm;

create table usuario(
	id_usuario int auto_increment not null  primary key,
    nombre_usuario varchar(60) not null,
    apellido_usuario varchar(60) not null,
    edad_usuario int not null
);

create table categoria(
	id_categoria int auto_increment not null primary key,
    nombre_categoria varchar(60) not null,
    descripcion_categoria varchar(150)
);
 
create table producto(
	id_producto int auto_increment not null primary key,
    nombre_producto varchar(80) not null,
    precio_producto decimal(10,2) not null,
    stock int not null,
    id_categoria int not null,
    constraint fk_producto_categoria
        foreign key (id_categoria)
        references categoria(id_categoria)
        on delete cascade
        on update cascade 
);
 
create table pedido(
	id_pedido int auto_increment not null primary key,
    fecha_pedido varchar(60) not null,
    total_pedido decimal(10,2) not null,
    id_usuario int not null,
    constraint fk_pedido_usuario
        foreign key (id_usuario)
        references usuario(id_usuario)
		on delete cascade
        on update cascade 
);
 
create table detalle_pedido(
	id_detalle int auto_increment not null primary key,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    id_pedido int not null,
    id_producto int not null,
    constraint fk_detalle_pedido
        foreign key (id_pedido)
        references pedido(id_pedido)
        on delete cascade
        on update cascade,
    constraint fk_detalle_producto
        foreign key (id_producto)
        references producto(id_producto)
		on delete cascade
        on update cascade 
);
INSERT INTO usuario (nombre_usuario, apellido_usuario, edad_usuario) VALUES
                                                                         ('Carlos', 'Gomez', 25),
                                                                         ('Maria', 'Lopez', 30),
                                                                         ('Juan', 'Perez', 22),
                                                                         ('Ana', 'Martinez', 28),
                                                                         ('Luis', 'Hernandez', 35);




INSERT INTO categoria (nombre_categoria, descripcion_categoria) VALUES
                                                                    ('Computadoras', 'Equipos de computo y laptops'),
                                                                    ('Accesorios', 'Perifericos como mouse y teclado'),
                                                                    ('Monitores', 'Pantallas y monitores'),
                                                                    ('Almacenamiento', 'Discos duros y memorias');


INSERT INTO producto (nombre_producto, precio_producto, stock, id_categoria) VALUES
                                                                                 ('Laptop Lenovo IdeaPad 3', 5500.00, 10, 1),
                                                                                 ('Mouse inalámbrico Logitech', 120.50, 50, 2),
                                                                                 ('Teclado mecánico Redragon', 350.99, 25, 2),
                                                                                 ('Monitor Samsung 24 pulgadas', 1800.75, 15, 3),
                                                                                 ('Disco duro externo 1TB', 650.00, 20, 4);



INSERT INTO pedido (fecha_pedido, total_pedido, id_usuario) VALUES
                                                                ('2026-04-10', 1250.50, 1),
                                                                ('2026-04-11', 350.00, 2),
                                                                ('2026-04-12', 899.99, 3),
                                                                ('2026-04-13', 2200.75, 4),
                                                                ('2026-04-14', 450.00, 5);

INSERT INTO detalle_pedido (cantidad, precio_unitario, id_pedido, id_producto) VALUES
                                                                                   (3, 5500.00, 16, 1),
                                                                                   (3, 350.99, 20, 3),
                                                                                   (1, 1800.75, 17, 2),
                                                                                   (2, 650.00, 18, 4),
                                                                                   (1, 120.50, 19, 5),
                                                                                   (2, 1800.75, 20, 2),
                                                                                   (5, 49.99, 20, 1);

INSERT INTO categoria (nombre_categoria, descripcion_categoria) VALUES
                                                                    ('Computadoras', 'Equipos de cómputo y laptops'),
                                                                    ('Accesorios', 'Periféricos como mouse, teclados y audífonos'),
                                                                    ('Monitores', 'Pantallas y monitores para PC'),
                                                                    ('Almacenamiento', 'Discos duros, SSD y memorias USB'),
                                                                    ('Impresoras', 'Impresoras y consumibles de impresión');
select * from usuario;