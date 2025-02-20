# Sistema de Jardineria - Proyecto Spring 

## 📄 Descripción y Objetivo del Proyecto

Este proyecto es un sistema de jardinería desarrollado con Spring Boot, cuyo objetivo es permitir a los clientes solicitar
servicios de jardineria como por ejemplo: "Cortador de Pasto" y "Podador de Árboles".

En este sistema existen 3 tipos de roles principales: **Administrador, Jardinero y Cliente**, cada uno con permisos y responsabilidades específicas.

El **Admin** tendrá permisos para bloquear o desbloquear jardineros y clientes, también podrá hacer un mantenimiento sobre los tipos de servicios de jardineria, 
ver a través de un listado todos los clientes y jardineros, lo recaudado, las transacciones, las contrataciones de clientes a jardineros.

Los **jardineros** podrán registrarse, activar su cuenta, inicar sesión, modificar su perfil, agregar fotos y descripciones de sus trabajos realizados(máximo 6),
ver que cliente solicita el servicios, aceptar o no realizar el servicios, cuando acepta se cobra un adelanto y se termina con el pago una vez finalizado el trabajo; 
en caso de cancelar se reintegrará el dinero al cliente.

Los **clientes** podrá registrarse, activar cuenta, iniciar sesión, modificar perfil, buscar jardinero por tipo de servicios, disponibilidad, elegir fecha,
solicitar servicios, si el jardinero acepta se le paga por adelantado y se termina de pagar una vez finalizado.

Los pagos se realizarán con mercado pago.

Está diseñado con un enfoque modular y escalable (Arquitectura Hexagonal), lo que permite integrar nuevas características en el futuro.
El proyecto también incorpora buenas prácticas de desarrollo mediante pruebas automatizadas (unitarias e integrales)
y está preparado para pipelines de CI/CD que garantizan la calidad y estabilidad del software en entornos de prueba  y más adelante también productivos


## ⚒ Tecnologías Utilizadas
- Java: Versión 17
- Spring Boot: Versión 3.3.4
- Spring Security
- Swagger/OpenAPI: Implementación para la documentación de la API
- Maven: Gestión de dependencias
- H2 Database: Base de datos en memoria para pruebas rápidas
- JUnit: Framework de pruebas unitarias e integración
- Docker: Para contenedores de servicios como Postgresql y Pgadmin

## 🏗 Arquitectura Utlizada actualmente
- Hexagonal

## IDE Utilizado
El proyecto se desarrolló utilizando IntelliJ IDEA como entorno de desarrollo integrado, 
pero también es compatible con otros IDEs como Eclipse o VS Code.


## Requisitos Previos
Contar con las siguientes herramientas antes de instalar el proyecto:

- Java JDK 17+
- Maven 3.8+
- Docker (para postgres, pgadmin u otros servicios adicionales)
- Git (para clonar el repositorio)


## ⚒ Instalación del proyecto

1. Clonar el repositorio
```
git clone https://github.com/ctnfimac/spring_cicd.git
cd spring_cicd
```

2. Cambia a la rama develop:
```
git checkout develop
```
3. Creo la base de datos:
```
docker-compose up -d

Para generar las tablas y Cargar datos de prueba se deja un script dentro de la carpeta
db llamado creacion_de_tablas_y_carga_de_datos.sql el cual puedo correr desde el pgadmin 
o conectandome a la base de datos dentro del contenedor de postgres 
```
4. Instala las dependencias del proyecto:
```
mvn clean install
```
5. Ejecuta la aplicación:
```
mvn spring-boot:run
```
6. La aplicación estará disponible en:
```
http://localhost:8080
```

## Documentación de la API (Swagger)
El proyecto utiliza Swagger para documentar las APIs. Una vez que el servidor está en ejecución se 
puede acceder a la misma en la siguiente URL:
```
http://127.0.0.1:8080/swagger-ui/index.html
```
![Image](https://github.com/user-attachments/assets/3f122286-1311-4005-9273-f1ad1b575c8e)


## Pruebas Unitarias y de Integración
El proyecto cuenta con pruebas unitarias y pruebas de integración para garantizar el correcto funcionamiento de las funcionalidades.

Ejecutar pruebas unitarias
```
mvn test -P unit-tests
```
Ejecutar pruebas de Integración
```
mvn test -P integration-tests
```
Ejecutar todas las Pruebas
```
mvn test
```

## Funcionalidades Actuales
- Iniciar Sesión con usuario y contraseña
- Implementado JWT para asegurar los endpoints. 
- Crear, modificar, Ver y Eliminar Servicios 
- Crear, modificar, Ver y Eliminar Tipos de Servicios 
- Crear, modificar, Ver y Eliminar Estados 
- Crear, modificar, Ver y Eliminar Roles 
- Crear, modificar, Ver y Eliminar Clientes 
- Crear, modificar, Ver y Eliminar Jardineros 
- Crear, modificar, Ver y Eliminar Trabajos Realizados 
- Crear, modificar, Ver y Eliminar Estados de Contratación
- Integración con herramientas de CI/CD para automatización de compilación y pruebas (en este caso con GitHub Actions).
- Implementación de pruebas unitarias e integración con JUnit.
- Soporte para base de datos en memoria (H2):
- Documentación de APIs con Swagger:
- Registro al Sistema por parte de los Jardineros (incluye activación de la cuenta desde el email)
- Registro al Sistema por parte de los Clientes (incluye activación de la cuenta desde el email)

## Funcionalidades a realizar
- Configuración Docker para ambiente de producción

Funcionalidades por parte del Jardinero:
- Dar de alta trabajos, modificar, ver y eliminar los trabajos realizados

Funcionalidades por parte del Cliente:
- Observar listado de jardineros
- Filtrar jardineros por tipo de servicios 
- Contratar jardinero


## Diagrama Entidad Relación de la base de datos
![Image](https://github.com/user-attachments/assets/3cba0016-2890-41b5-8121-3c825e7d4661)



## API Endpoints

### Autenticación
- `POST /api/auth/login` - Inicio de Sesión de los Usuarios

### Registro de Usuarios
- `POST /api/registro/jardinero` - Registro de un Jardinero
- `GET /api/registro/activar_jardinero?token={token}&id={id}` - Activar la cuenta de un Jardinero
- `POST /api/registro/cliente` - Registro de un Cliente
- `GET /api/registro/activar_cliente?token={token}&id={id}` - Activar la cuenta de un Cliente

### Jardineros
- `GET /api/jardinero` - Obtiene todos los jardineros
- `GET /api/jardinero/{id}` - Obtiene un jardinero por ID
- `POST /api/jardinero` - Crea un nuevo jardinero
- `PUT /api/jardinero/{id}` - Actualiza un jardinero
- `DELETE /api/jardinero/{id}` - Elimina un jardinero

### Clientes
- `GET /api/cliente` - Obtiene todos los clientes
- `GET /api/cliente/{id}` - Obtiene un cliente por ID
- `POST /api/cliente` - Crea un nuevo cliente
- `PUT /api/cliente/{id}` - Actualiza un cliente
- `DELETE /api/cliente/{id}` - Elimina un cliente

### Roles
- `GET /api/rol` - Obtiene todos los roles
- `GET /api/rol/{id}` - Obtiene un rol por ID
- `POST /api/rol` - Crea un nuevo rol
- `PUT /api/rol/{id}` - Actualiza un rol
- `DELETE /api/rol/{id}` - Elimina un rol

### Estados
- `GET /api/estado` - Obtiene todos los estados
- `GET /api/estado/{id}` - Obtiene un estado por ID
- `POST /api/estado` - Crea un nuevo estado
- `PUT /api/estado/{id}` - Actualiza un estado
- `DELETE /api/estado/{id}` - Elimina un estado

### Estados de Compra
- `GET /api/estadocompra` - Obtiene todos los estados de compras
- `GET /api/estadocompra/{id}` - Obtiene un estado de compra por ID
- `POST /api/estadocompra` - Crea un nuevo estado de compra
- `PUT /api/estadocompra/{id}` - Actualiza un estado de compra
- `DELETE /api/estadocompra/{id}` - Elimina un estado de compra

### Tipo de Servicios
- `GET /api/tipodeservicio` - Obtiene todos los tipos de servicios
- `GET /api/tipodeservicio/{id}` - Obtiene un tipo de servicio por ID
- `POST /api/tipodeservicio` - Crea un nuevo tipo de servicio
- `PUT /api/tipodeservicio/{id}` - Actualiza un tipo de servicio
- `DELETE /api/tipodeservicio/{id}` - Elimina un tipo de servicio

### Servicios
- `GET /api/servicio` - Obtiene todos los servicios
- `GET /api/servicio/{UUID}` - Obtiene un servicio por UUID
- `POST /api/servicio` - Crea un nuevo servicio
- `PUT /api/servicio/{UUID}` - Actualiza un servicio
- `DELETE /api/servicio/{UUID}` - Elimina un servicio

### Estados de Contratación
- `GET /api/estadodecontratacion` - Obtiene todos los estados de contratación
- `GET /api/estadodecontratacion/{id}` - Obtiene un estado de contratacion por ID
- `POST /api/estadodecontratacion` - Crea un nuevo estado de contratacion
- `PUT /api/estadodecontratacion/{id}` - Actualiza un estado de contratacion
- `DELETE /api/estadodecontratacion/{id}` - Elimina un estado de contratacion

### Trabajos Realizados
- `GET /api/trabajorealizado` - Obtiene todos los trabajos realizados
- `GET /api/trabajorealizado/{id}` - Obtiene un trabajo realizado por ID
- `POST /api/trabajorealizado` - Crea un nuevo trabajo realizado
- `PUT /api/trabajorealizado/{id}` - Actualiza un trabajo realizado
- `DELETE /api/trabajorealizado/{id}` - Elimina un trabajo realizado

### Contrataciones
- `GET /api/contrata` - Obtiene todas las contrataciones
- `GET /api/contrata/{id}` - Obtiene una contratación por ID


