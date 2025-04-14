# Servicio de Usuarios

## 📄 Descripción
Este microservicio forma parte del proyecto [Sistema de Jardineria](https://github.com/ctnfimac/SistemaDeJardineria).
Se encarga de la gestión de usuarios, incluyendo funcionalidades como el registro, 
inicio de sesión y activación de cuentas.
También administra la seguridad de la aplicación mediante la implementación de autenticación y autorización
utilizando Spring Security y JWT (JSON Web Tokens).


## ⚒ Tecnologías Utilizadas
- Java: Versión 17
- Spring Boot: Versión 3.3.4
- Spring Security
- Swagger/OpenAPI: Implementación para la documentación de la API
- Maven: Gestión de dependencias
- H2 Database: Base de datos en memoria para pruebas rápidas
- JUnit: Framework de pruebas unitarias e integración
- Docker: Para contenedores de servicios como Postgresql y Pgadmin

## 🏗 Arquitectura Utilizada
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



## Diagrama Entidad Relación de la base de datos
![Image](https://github.com/user-attachments/assets/5536eff3-bef5-4ee9-a422-4db12760ec55)



## API Endpoints

### Autenticación
- `POST /api/v1/auth/login` - Inicio de Sesión de los Usuarios

### Registro de Usuarios
- `POST /api/v1/register/gardener` - Registro de un Jardinero
- `GET /api/v1/register/activate_gardener?token={token}&id={id}` - Activar la cuenta de un Jardinero
- `POST /api/v1/register/client` - Registro de un Cliente
- `GET /api/v1/register/activate_client?token={token}&id={id}` - Activar la cuenta de un Cliente

### Jardineros
- `GET /api/v1/gardener` - Obtiene todos los jardineros
- `GET /api/v1/gardener/{id}` - Obtiene un gardener por ID
- `POST /api/v1/gardener` - Crea un nuevo gardener
- `PUT /api/v1/gardener/{id}` - Actualiza un gardener
- `DELETE /api/v1/gardener/{id}` - Elimina un gardener

### Clientes
- `GET /api/v1/client` - Obtiene todos los clientes
- `GET /api/v1/client/{id}` - Obtiene un client por ID
- `POST /api/v1/client` - Crea un nuevo client
- `PUT /api/v1/client/{id}` - Actualiza un client
- `DELETE /api/v1/client/{id}` - Elimina un client

### Roles
- `GET /api/v1/role` - Obtiene todos los roles
- `GET /api/v1/role/{id}` - Obtiene un rol por ID
- `POST /api/v1/role` - Crea un nuevo rol
- `PUT /api/v1/role/{id}` - Actualiza un rol
- `DELETE /api/v1/role/{id}` - Elimina un rol

### Estados
- `GET /api/v1/estate` - Obtiene todos los estados
- `GET /api/v1/estate/{id}` - Obtiene un estado por ID
- `POST /api/v1/estate` - Crea un nuevo estado
- `PUT /api/v1/estate/{id}` - Actualiza un estado
- `DELETE /api/v1/estate/{id}` - Elimina un estado

## Funcionalidades Actuales
- Iniciar Sesión con usuario y contraseña
- Implementado JWT para asegurar los endpoints.
- Crear, modificar, Ver y Eliminar Estados
- Crear, modificar, Ver y Eliminar Roles
- Crear, modificar, Ver y Eliminar Clientes
- Crear, modificar, Ver y Eliminar Jardineros
- Integración con herramientas de CI/CD para automatización de compilación y pruebas (en este caso con GitHub Actions).
- Implementación de pruebas unitarias e integración con JUnit.
- Soporte para base de datos en memoria (H2):
- Documentación de APIs con Swagger:
- Registro al Sistema por parte de los Jardineros (incluye activación de la cuenta desde el email)
- Registro al Sistema por parte de los Clientes (incluye activación de la cuenta desde el email)