CREATE TABLE rol(
	id SERIAL PRIMARY KEY,
	descripcion varchar(15) UNIQUE
);

CREATE TABLE estado(
	id SERIAL PRIMARY KEY,
	descripcion varchar(15) UNIQUE
);


CREATE TABLE persona(
	id BIGSERIAL PRIMARY KEY,
	nombre varchar(20),
	apellido varchar(20),
	contrasenia varchar(72),
	id_rol integer,
	id_estado integer,
	CONSTRAINT fk_persona_rol FOREIGN KEY(id_rol) REFERENCES rol(id),
	CONSTRAINT fk_persona_estado FOREIGN KEY(id_estado) REFERENCES estado(id)
);



CREATE TABLE comprador(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(10) unique,
	direccion varchar(50),
	email varchar(30) unique,
	latitud varchar(20),
	longitud varchar(20),
	id_persona integer NOT NULL,
	CONSTRAINT fk_comprador_persona FOREIGN KEY(id_persona) REFERENCES persona(id)
);

CREATE TABLE vendedor(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(10) unique,
	email varchar(30) unique,
	id_persona integer NOT NULL,
	CONSTRAINT fk_vendedor_persona FOREIGN KEY(id_persona) REFERENCES persona(id)
);



CREATE TABLE producto(
	id BIGSERIAL PRIMARY KEY,
	nombre varchar(30),
	descripcion varchar(100),
	precio decimal(10,2),
	stock integer default 0,
	id_vendedor integer NOT NULL,
	CONSTRAINT fk_producto_vendedor FOREIGN KEY(id_vendedor) REFERENCES vendedor(id)
);


CREATE TABLE estado_compra(
	id SERIAL PRIMARY KEY,
	descripcion varchar(15) UNIQUE
);


CREATE TABLE compra(
	id BIGSERIAL PRIMARY KEY,
	precio_total decimal(12,2),
	id_comprador integer NOT NULL,
	id_estado_compra integer NOT NULL,
	CONSTRAINT fk_compra_comprador FOREIGN KEY(id_comprador) REFERENCES comprador(id),
	CONSTRAINT fk_compra_estado_compra FOREIGN KEY(id_estado_compra) REFERENCES estado(id)
);

CREATE TABLE compra_producto(
	id_compra integer,
	id_producto integer,
	cantidad integer default 1,
	CONSTRAINT fk_compra_producto_producto FOREIGN KEY(id_producto) REFERENCES producto(id),
	CONSTRAINT fk_compra_producto_compra FOREIGN KEY(id_compra) REFERENCES compra(id),
	CONSTRAINT unique_compra_producto UNIQUE (id_compra, id_producto)
);


-- CARGA DE DATOS PARA LAS PRUEBAS
INSERT INTO rol(descripcion)
VALUES('ADMIN'),
('COMPRADOR'),
('VENDEDOR');

INSERT INTO estado(descripcion)
VALUES('ACTIVO'),
('BLOQUEADO');

INSERT INTO persona(nombre, apellido, contrasenia, id_rol, id_estado)
VALUES('Christian', 'Peralta', '$2y$10$0/oN1VW5zp6fnZxLf7lGEeD8fY/ogvtjHKjInfzPAeZ5WIGZLia8e', 1, 1),
('Cubillas', 'Diaz', '$2y$10$im7hhroMo8qNm75kZjy1KuaMmdNQ3p5TjsIO3ZR8AyjWlfoiunthC', 3, 1),
('Miyagui', 'Silva', '$2y$10$jdVaCKSkZDiFGHZ7vBrKqOYhssQMtbkGbVVcIrqvBkYkeF5qaGxVy', 3, 1),
('Peluca', 'Milei', '$2y$10$3QaYSBEbgrdzuqrpJiEMHOD07GDj0CQVVAjzJvSiziuLFhXMGoAry', 2, 1),
('Donald', 'Trump', '$2y$10$cEwY668k5wGd79zK4GBBcu3k/JOIDbn0u75O1xFkkvVXQzCWYQKAu', 2, 1);
select * from persona;

INSERT INTO comprador(id_persona, telefono, direccion, email, latitud, longitud)
VALUES(4, '1121368752', 'lacarra 535', 'peluca@gmail.com', '-34.640065', '-58.481578'),
(5, '1150806210', 'las tunas 11122', 'donald@gmail.com', '-34.639639', '-58.521601');


INSERT INTO vendedor(id_persona, telefono, email)
VALUES(2, '1578410121', 'cubillas@gmail.com'),
(3, '1160302040', 'miyagui@gmail.com');
