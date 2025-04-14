-- Creo la extensión uuids para las claves primarias

-- BORRO TABLAS

DROP TABLE servicio;
DROP TABLE contrata;
DROP TABLE trabajo_realizado;
DROP TABLE jardinero;
DROP TABLE cliente;
DROP TABLE persona;

DROP TABLE estado;
DROP TABLE rol;
DROP TABLE estado_contratacion;
DROP TABLE tipo_de_servicio;


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
	email varchar(30) unique,
	contrasenia varchar(72),
	token_activacion varchar(40),
	rol_id integer NOT NULL,
	estado_id integer NOT NULL,
	CONSTRAINT fk_persona_rol FOREIGN KEY(rol_id) REFERENCES rol(id),
	CONSTRAINT fk_persona_estado FOREIGN KEY(estado_id) REFERENCES estado(id)
);



CREATE TABLE cliente(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(12) unique,
	direccion varchar(50),
	latitud varchar(20),
	longitud varchar(20),
	persona_id integer NOT NULL,
	CONSTRAINT fk_cliente_persona FOREIGN KEY(persona_id) REFERENCES persona(id)
);

CREATE TABLE jardinero(
	id BIGSERIAL PRIMARY KEY,
	telefono varchar(12) unique,
	presentacion TEXT,
	persona_id integer NOT NULL,
	CONSTRAINT fk_jardinero_persona FOREIGN KEY(persona_id) REFERENCES persona(id)
);


-- CARGA DE DATOS PARA LAS PRUEBAS
INSERT INTO rol(descripcion)
VALUES('ADMIN'),
('CLIENTE'),
('JARDINERO');

INSERT INTO estado(descripcion)
VALUES('ACTIVO'),
('SIN_ACTIVAR'),
('BLOQUEADO');

INSERT INTO persona(nombre, apellido, email, contrasenia, rol_id, estado_id)
VALUES('Christian', 'Peralta', 'christian@gmail.com','$2y$10$bNIWz0bB3V6PuL2eg554ju6bBZzOa8.SV999LrlX9okWFKyxWmplG', 1, 1),
('Cubillas', 'Diaz', 'cubillas@gmail.com','$2y$10$3lUiqen09.KhPpOrm.eWNu/KG5hh0VuaWLNWRNF1UMCp/dwwmr8Ci', 3, 1),
('Miyagui', 'Silva', 'miyagui@gmail.com','$2y$10$bfdnFSYxpIZxaw6RWjOMEubk5zqoI/MZL.p2wkReoRlxjwn1Wxgd2', 3, 1),
('Peluca', 'Milei', 'peluca@gmail.com','$2y$10$WmIKjs9gPavabhDI.BZ6NOe3BQka8By1Lt8H/jWbIbPHpZRSFwEj6', 2, 1),
('Donald', 'Trump', 'donald@gmail.com','$2y$10$LgUHLBlQx0lUfq7kOh5.KuL9aznreuhrPz5Xcoy1VVBy1G2qtb8Fq', 2, 1);


INSERT INTO cliente(persona_id, telefono, direccion, latitud, longitud)
VALUES(4, '1121368752', 'lacarra 535', '-34.640065', '-58.481578'),
(5, '1150806210', 'las tunas 11122', '-34.639639', '-58.521601');


INSERT INTO jardinero(persona_id, telefono, presentacion)
VALUES(2, '1578410121', 'Mi nombre es cubillas etc etc'),
(3, '1160302040', 'Soy miyagui el karateca de la jardineria etc etc');


