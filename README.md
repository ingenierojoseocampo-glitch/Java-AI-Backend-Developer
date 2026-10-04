# VetTurno

## Historia

VetTurno es una aplicación backend desarrollada para la Veterinaria Huellitas.

La veterinaria necesita una solución que permita gestionar la información de sus propietarios, mascotas, veterinarios y citas médicas.

El sistema permitirá registrar usuarios, administrar mascotas y propietarios, gestionar veterinarios y crear citas, aplicando reglas de validación y control de acceso según el rol del usuario.

## Tecnologías

- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- JWT
- Bean Validation
- Swagger / OpenAPI

## Cómo ejecutar

### Requisitos

- Java 17
- Maven
- MySQL

### Base de datos

Crear una base de datos MySQL llamada `VetTurno`.

Luego configurar las credenciales de conexión en:

`src/main/resources/application.properties`

### Ejecutar el proyecto

Desde la carpeta raíz del proyecto ejecutar:

```bash
./mvnw spring-boot:run