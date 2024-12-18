# Sistema de Ventas - Proyecto Spring 

## Descripción y Objetivo del Proyecto

Este proyecto es un sistema de ventas de productos desarrollado con Spring Boot, cuyo objetivo es gestionar las funcionalidades principales de una plataforma de comercio. 
En este sistema existen tres roles principales: **Administrador, Vendedor y Comprador**, cada uno con permisos y responsabilidades específicas.

Además incluye funcionalidades como la gestión de usuarios, productos y pedidos, permitir el seguimiento de transacciones y la interacción entre vendedores y compradores. 
Está diseñado con un enfoque modular y escalable, lo que permite integrar nuevas características en el futuro.

El proyecto también incorpora buenas prácticas de desarrollo mediante pruebas automatizadas (unitarias e integrales) 
y está preparado para pipelines de CI/CD que garantizan la calidad y estabilidad del software en entornos de prueba  y más adelante también productivos


## Tecnologías Utilizadas
- Java: Versión 17
- Spring Boot: Versión 3.3.4
- Swagger/OpenAPI: Implementación para la documentación de la API
- Maven: Gestión de dependencias
- H2 Database: Base de datos en memoria para pruebas rápidas
- JUnit: Framework de pruebas unitarias e integración
- Docker: Para contenedores de servicios como Postgresql y Pgadmin



## IDE Utilizado
El proyecto se desarrolló utilizando IntelliJ IDEA como entorno de desarrollo integrado, 
pero también es compatible con otros IDEs como Eclipse o VS Code.


## Requisitos Previos
Contar con las siguientes herramientas antes de instalar el proyecto:

- Java JDK 17+
- Maven 3.8+
- Docker (para postgres, pgadmin u otros servicios adicionales)
- Git (para clonar el repositorio)


## Instalación del proyecto

1. Clonar el repositorio
```
git clone https://github.com/ctnfimac/spring_cicd.git
cd spring_cicd
```

2. Cambia a la rama develop:
```
git checkout develop
```
3. Instala las dependencias:
```
mvn clean install
```
4. Ejecuta la aplicación:
```
mvn spring-boot:run
```
5. La aplicación estará disponible en:
```
http://localhost:8080
```

## Documentación de la API (Swagger)
El proyecto utiliza Swagger para documentar las APIs. Una vez que el servidor está en ejecución se 
puede acceder a la misma en la siguiente URL:
```
http://127.0.0.1:8080/swagger-ui/index.html
```
![swagger_github](https://github.com/user-attachments/assets/10ec147c-e300-489b-89c9-3b2b9384b281)

## Pruebas automatizadas
El proyecto incluye pruebas unitarias y pruebas de integración para garantizar el correcto funcionamiento de las funcionalidades.

## Ejecutar Pruebas

- Pruebas unitarias
```
mvn test -P unit-tests
```
- Pruebas de Integración
```
mvn test -P integration-tests
```
- Todas las Pruebas
```
mvn test
```

## Funcionalidades Actuales
1. Gestión de entidades base (CRUD):
    - Ejemplo: Gestión de Vendedores con operaciones básicas (crear, leer, actualizar y eliminar).

2. Documentación de APIs con Swagger:
    - Auto-generación de endpoints y estructura API con OpenAPI.

3. Soporte para base de datos en memoria (H2):
    - Permite pruebas rápidas y configuración sencilla para entornos de desarrollo.

4. Pruebas Automatizadas:
    - Implementación de pruebas unitarias e integración con JUnit.

5. Configuración CI/CD:
    - Integración con herramientas de CI/CD para automatización de compilación y pruebas (en este caso con GitHub Actions).

## Funcionalidades Futuras
1. Autenticación y Autorización:
    - Implementación de OAuth2 o JWT para asegurar los endpoints.
    - Registro de usuarios
    - Activar el usuario por medio de un email
    - Login de usuarios
    - Cerrar Sesión
    

2. Realización de compras por parte de los compradores (Carrito)
    - Seleccionar uno o varios productos 
    - Eliminar productos de la selección

3. Puesta de ventas de productos por parde de los vendedores
    - Crud completo de productos por parte de los vendedores


## Diagrama Entidad Relación de la base de datos
![proyecto_ventas](https://github.com/user-attachments/assets/60b35607-a9bf-474d-abef-4c20ec155877)






