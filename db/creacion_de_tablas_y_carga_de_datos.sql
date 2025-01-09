-- BORRO TABLAS

/*DROP TABLE servicio;
DROP TABLE contrata;
DROP TABLE trabajo_realizado;
DROP TABLE jardinero;
DROP TABLE cliente;
DROP TABLE persona;

DROP TABLE estado;
DROP TABLE rol;
DROP TABLE estado_contratacion;
DROP TABLE tipo_de_servicio;
*/

-- CREACIÓN DE TABLAS
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
	rol_id integer NOT NULL,
	estado_id integer NOT NULL,
	CONSTRAINT fk_persona_rol FOREIGN KEY(rol_id) REFERENCES rol(id),
	CONSTRAINT fk_persona_estado FOREIGN KEY(estado_id) REFERENCES estado(id)
);



CREATE TABLE cliente(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(12) unique,
	direccion varchar(50),
	email varchar(30) unique,
	latitud varchar(20),
	longitud varchar(20),
	persona_id integer NOT NULL,
	CONSTRAINT fk_cliente_persona FOREIGN KEY(persona_id) REFERENCES persona(id)
);

CREATE TABLE jardinero(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(12) unique,
	email varchar(30) unique,
	presentacion TEXT,
	persona_id integer NOT NULL,
	CONSTRAINT fk_jardinero_persona FOREIGN KEY(persona_id) REFERENCES persona(id)
);


CREATE TABLE trabajo_realizado(
	 id SERIAL PRIMARY KEY,
	 foto varchar(50) NOT NULL,
	 descripcion TEXT,
	 jardinero_id integer NOT NULL,
	 CONSTRAINT fk_trabajorealizado_jardinero FOREIGN KEY(jardinero_id) REFERENCES jardinero(id)
);


CREATE TABLE tipo_de_servicio(
	id SERIAL PRIMARY KEY,
	foto varchar(50) NOT NULL UNIQUE,
	nombre varchar(30)
);

CREATE TABLE servicio(
	id SERIAL PRIMARY KEY,
	descripcion varchar(100),
	precio decimal(10,2) NOT NULL,
	jardinero_id integer NOT NULL,
	tipodeservicio_id integer NOT NULL,
	CONSTRAINT fk_servicio_jardinero FOREIGN KEY(jardinero_id)
			REFERENCES jardinero(id),
	CONSTRAINT fk_servicio_tipodeservicio FOREIGN KEY(tipodeservicio_id)
			REFERENCES tipo_de_servicio(id),
	CONSTRAINT unique_cliente_jardinero UNIQUE (jardinero_id, tipodeservicio_id)
);


CREATE TABLE estado_contratacion(
	id SERIAL PRIMARY KEY,
	descripcion varchar(15) UNIQUE
);


CREATE TABLE contrata(
	id BIGSERIAL PRIMARY KEY,
	precio_total decimal(12,2),
	fecha date NOT NULL,
	cliente_id integer NOT NULL,
	jardinero_id integer NOT NULL,
	estadocontratacion_id integer NOT NULL,
	CONSTRAINT fk_contrata_cliente FOREIGN KEY(cliente_id)
			REFERENCES cliente(id),
	CONSTRAINT fk_contrata_jardinero FOREIGN KEY(jardinero_id)
			REFERENCES jardinero(id),
	CONSTRAINT fk_contrata_estadocontratacion FOREIGN KEY(estadocontratacion_id)
			REFERENCES estado_contratacion(id)
);


-- CARGA DE DATOS PARA LAS PRUEBAS
INSERT INTO rol(descripcion)
VALUES('ADMIN'),
('COMPRADOR'),
('VENDEDOR');

INSERT INTO estado(descripcion)
VALUES('ACTIVO'),
('BLOQUEADO');

INSERT INTO persona(nombre, apellido, contrasenia, rol_id, estado_id)
VALUES('Christian', 'Peralta', '$2y$10$0/oN1VW5zp6fnZxLf7lGEeD8fY/ogvtjHKjInfzPAeZ5WIGZLia8e', 1, 1),
('Cubillas', 'Diaz', '$2y$10$im7hhroMo8qNm75kZjy1KuaMmdNQ3p5TjsIO3ZR8AyjWlfoiunthC', 3, 1),
('Miyagui', 'Silva', '$2y$10$jdVaCKSkZDiFGHZ7vBrKqOYhssQMtbkGbVVcIrqvBkYkeF5qaGxVy', 3, 1),
('Peluca', 'Milei', '$2y$10$3QaYSBEbgrdzuqrpJiEMHOD07GDj0CQVVAjzJvSiziuLFhXMGoAry', 2, 1),
('Donald', 'Trump', '$2y$10$cEwY668k5wGd79zK4GBBcu3k/JOIDbn0u75O1xFkkvVXQzCWYQKAu', 2, 1);


INSERT INTO cliente(persona_id, telefono, direccion, email, latitud, longitud)
VALUES(4, '1121368752', 'lacarra 535', 'peluca@gmail.com', '-34.640065', '-58.481578'),
(5, '1150806210', 'las tunas 11122', 'donald@gmail.com', '-34.639639', '-58.521601');


INSERT INTO jardinero(persona_id, telefono, email, presentacion)
VALUES(2, '1578410121', 'cubillas@gmail.com', 'Mi nombre es cubillas etc etc'),
(3, '1160302040', 'miyagui@gmail.com', 'Soy miyagui el karateca de la jardineria etc etc');


INSERT INTO trabajo_realizado(foto, descripcion, jardinero_id)
VALUES('/fotos/trabajo1.jpg', 'trabajo de corte de pasto realizado en x lugar', 1),
('/fotos/1/trabajo2.jpg', 'trabajo de Poda realizado para la señora Victoria', 1),
('/fotos/2/trabajo1.jpg', 'trabajo dificil de poda de pino realizado en ramos mejia', 2);


INSERT INTO tipo_de_servicio(nombre)
VALUES('Corte de Pasto'),
('Poda de Arboles'),
('Fertilizar'),
('Protección de plantas');


INSERT INTO servicio(descripcion, precio, jardinero_id, tipodeservicio_id)
VALUES('Descripcion puesta por el jardinero dando valor a su servicio', 5000.0, 1, 1),
('Descripcion puesta por el jardinero dando valor a su servicio', 6000.0, 2, 1),
('Descripcion puesta por el jardinero dando valor a su servicio', 7000.0, 1, 2),
('Descripcion puesta por el jardinero dando valor a su servicio', 8000.0, 2, 3),
('Descripcion puesta por el jardinero dando valor a su servicio', 9000.0, 2, 2),
('Descripcion puesta por el jardinero dando valor a su servicio', 10000.0, 1, 4),
('Descripcion puesta por el jardinero dando valor a su servicio', 11000.0, 1, 3),
('Descripcion puesta por el jardinero dando valor a su servicio', 12000.0, 2, 4);


INSERT INTO estado_contratacion(descripcion)
VALUES('RESERVADO'),
('PROCESO'),
('FINALIZADO');


INSERT INTO contrata(precio_total, fecha, cliente_id, jardinero_id, estadocontratacion_id)
VALUES(5000.0, '2025-02-01', 1, 1, 1),
(10000.0, '2025-03-02', 2, 1, 2),
(7000.0, '2025-05-03', 2, 2, 1),
(12000.0, '2025-07-04', 2, 1, 1),
(11000.0, '2025-08-05', 1, 2, 3);

