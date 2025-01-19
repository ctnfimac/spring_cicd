# Sistema de Jardineria - Proyecto Spring 

## Descripción y Objetivo del Proyecto

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
![Image](https://github.com/user-attachments/assets/3f122286-1311-4005-9273-f1ad1b575c8e)
(por ahora esta captura es de endpoints anteriores a la migración)

## Pruebas automatizadas
El proyecto cuenta con pruebas unitarias y pruebas de integración para garantizar el correcto funcionamiento de las funcionalidades.

## Para ejecutar Pruebas

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
1. Iniciar Sesión con usuario y contraseña
   - Implementado JWT para asegurar los endpoints.
2. Dar de alta, modificar, ver y eliminar Servicios
3. Dar de alta, modificar, ver y eliminar Tipos de Servicios
4. Dar de alta, modificar, ver y eliminar Estados
5. Configuración CI/CD:
   - Integración con herramientas de CI/CD para automatización de compilación y pruebas (en este caso con GitHub Actions).
6. Pruebas Automatizadas:
   - Implementación de pruebas unitarias e integración con JUnit.
7. Soporte para base de datos en memoria (H2):
   - Permite pruebas rápidas y configuración sencilla para entornos de desarrollo.
8. Documentación de APIs con Swagger:
   - Auto-generación de endpoints y estructura API con OpenAPI.


## Funcionalidades a realizar
Funcionalidades por parte del Jardinero:
1. Dar de alta trabajos, modificar, ver y eliminar los trabajos realizados

Funcionalidades por parte del Cliente:
1. Observar listado de jardineros
2. Filtrar jardineros por tipo de servicios
3. Contratar jardinero

## Funcionalidades Futuras
1. Configuración Docker para ambiente de producción:
2. Autenticación y Autorización:
    - Registro de usuarios
    - Activar el usuario por medio de un email
    


## Diagrama Entidad Relación de la base de datos
![proyecto_ventas](https://github.com/user-attachments/assets/94747463-dc80-4535-9fce-aaebd531a8af)






