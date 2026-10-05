VetTurno

API REST para organizar la agenda digital de la **Veterinaria Huellitas**. Este proyecto permite que recepción gestione responsables, mascotas, veterinarios y citas desde una API documentada, con información persistida en MySQL y acceso controlado por roles.

>
## Historia y contexto

Doña Marta abrió Veterinaria Huellitas hace seis años. Atiende con el doctor Andrés y Paula, quien recibe llamadas y organiza las citas entre un cuaderno y conversaciones de mensajería. La información dispersa puede producir reservas dobles, nombres incorrectos y pérdida de datos de contacto.

VetTurno propone una API para centralizar responsables y mascotas, administrar veterinarios y agendar citas sin cruces para un mismo profesional. Paula representa `USER`; Doña Marta, `ADMIN`.

## Alcance

Incluye registro y login, roles `USER`/`ADMIN`, gestión de propietarios, mascotas, veterinarios y citas, persistencia MySQL, validaciones, errores controlados y documentación Swagger/OpenAPI. La agenda se puede consultar completa y filtrar por veterinario.

Quedan fuera la historia clínica, pagos, inventario, recordatorios, interfaz web/móvil y despliegue en la nube.

## Tecnologías

- Java 17.
- Spring Boot 4.1.1 y Maven Wrapper.
- Spring Web MVC, Spring Data JPA/Hibernate y MySQL Connector/J.
- Spring Security, BCrypt y JWT (JJWT 0.13.0).
- Bean Validation y springdoc OpenAPI/Swagger UI 3.1.0.

## Arquitectura y modelo

El código organiza responsabilidades en `controller`, `service`, `repository`, `model`, `dto`, `security`, `config` y `exception`, bajo `com.VetTurno.VetTurno`.

Modelo observado:

- `Propietario` tiene varias `Mascota`; cada mascota referencia a un propietario mediante `propietario_id`.
- `Cita` referencia una `Mascota` y un `Veterinario` mediante llaves foráneas.
- `Usuario` guarda email único, contraseña codificada y rol (`USER` o `ADMIN`).
- Las respuestas usan DTO. `CitaDTO` contiene ids y nombres de mascota y veterinario, pero actualmente no incluye el propietario que pide el contrato de la actividad.

Los controllers reciben HTTP y delegan en services; los services resuelven relaciones y reglas de agenda; los repositorios consultan y persisten entidades. `GlobalExceptionHandler` convierte validaciones y reglas de negocio en `ApiError`.

## Requisitos previos

- Git y MySQL Server.
- JDK compatible con el `pom.xml` actual.
- Maven no necesita instalación si se usa el wrapper incluido (`mvnw` / `mvnw.cmd`).
- El IDE que prefiera.

## Preparar MySQL y la configuración local

1. Inicia MySQL.
2. Crea la base `VetTurno`, que coincide con la URL JDBC declarada en `src/main/resources/application.properties`.

   ```sql
   CREATE DATABASE VetTurno;
   ```

3. Crea un usuario MySQL local con permisos solo sobre esa base; evita usar la cuenta administrativa.
4. Proporciona la conexión con las variables de entorno Spring `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD` antes de iniciar. Usa los valores propios de tu servidor, no los guardes en Git.

Formato de URL local:

```text
jdbc:mysql://localhost:3306/VetTurno
```

`application.properties` tiene `ddl-auto=update` y registro SQL activado para desarrollo. No uses la actualización automática de esquema como sustituto de migraciones en producción.


## Ejecutar la aplicación

Abrir con su IDE de preferencia.

Con MySQL activo y la configuración local preparada.

El puerto por defecto es `8080`. Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Spring Security permite las rutas `GET /swagger-ui/**`, `GET /swagger-ui.html` y `GET /v3/api-docs/**` sin token.

## Registro, login y Swagger Authorize

1. Ejecuta `POST /api/auth/register` con email y contraseña válidos. El registro crea siempre un usuario `USER` y devuelve `{ "token": "..." }`.

   ```json
   {
     "email": "paula@example.com",
     "password": "clave-de-prueba"
   }
   ```

   El DTO actual exige contraseña de al menos 4 caracteres. Utiliza credenciales de prueba propias; no uses ese ejemplo como contraseña real.
2. Ejecuta `POST /api/auth/login` con el mismo email y contraseña. La respuesta contiene un JWT válido por una hora según `JwtService`.
3. En Swagger pulsa **Authorize** y pega el token en el esquema HTTP Bearer `bearerAuth`; Swagger agrega el prefijo `Bearer`.
4. Cierra el diálogo y prueba una ruta protegida, por ejemplo `GET /api/citas`.
5. No guardes el JWT en capturas ni lo publiques: funciona como credencial temporal.

El registro no permite crear `ADMIN`: `AuthService` asigna `USER`. La actividad indica habilitar el primer administrador de forma controlada en la base de datos; después debe iniciar sesión otra vez para obtener un token con ese rol.

## Endpoints y roles

`USER` y `ADMIN` pueden consultar los recursos con `GET`; ambos pueden crear propietarios, mascotas y citas. Solo `ADMIN` crea veterinarios. Registro, login y Swagger son públicos.

| Método | Ruta implementada | Acceso | Resultado / notas |
|---|---|---|---|
| `POST` | `/api/auth/register` | Público | Registra `USER` y devuelve JWT; prueba: `200` |
| `POST` | `/api/auth/login` | Público | Autentica y devuelve JWT; prueba: `200` |
| `GET` | `/api/propietarios` | `USER` / `ADMIN` | Lista propietarios |
| `POST` | `/api/propietarios` | `USER` / `ADMIN` | Crea propietario; `201` |
| `GET` | `/api/mascotas` | `USER` / `ADMIN` | Lista mascotas |
| `POST` | `/api/mascotas` | `USER` / `ADMIN` | Crea mascota; `201` |
| `GET` | `/api/veterinarios` | Autenticado | Lista veterinarios |
| `POST` | `/api/veterinarios` | Solo `ADMIN` | `USER`: `403`; `ADMIN`: `201` |
| `GET` | `/api/citas` | `USER` / `ADMIN` | Lista citas; acepta `veterinarioId` opcional |
| `GET` | `/api/citas?veterinarioId=` | `USER` / `ADMIN` | Filtra por veterinario; se probó id 3 con `200` |
| `POST` | `/api/citas` | `USER` / `ADMIN` | Crea cita; `201`; valida fecha futura, relaciones y horario |

### Ejemplos de cuerpos

Crear propietario (`POST /api/propietarios`):

```json
{
  "nombre": "Pedro González",
  "email": "pedro@example.com",
  "telefono": "3001234567"
}
```

Crear mascota (`POST /api/mascotas`; usa un `propietarioId` existente):

```json
{
  "nombre": "Max",
  "especie": "Perro",
  "raza": "Labrador",
  "propietarioId": 1
}
```

Crear veterinario (`POST /api/veterinarios`; solo `ADMIN`):

```json
{
  "nombre": "Andrés",
  "especialidad": "Medicina general"
}
```

Crear cita (`POST /api/citas`; ids existentes y fecha futura):

```json
{
  "fechaHora": "2030-10-10T10:00:00",
  "motivo": "Consulta general",
  "mascotaId": 1,
  "veterinarioId": 1
}
```

## Matriz de pruebas manuales

Estos resultados se dieron durante las pruebas: los casos 1-13 se marcaron como completados; también se confirmaron 14 y 15.

| # | Escenario | Resultado reportado |
|---:|---|---|
| 1 | Aplicación inicia con MySQL disponible | Correcto |
| 2 | Registro válido de Paula | `200`; devuelve token |
| 3 | Email inválido y contraseña corta al registrar | `400` |
| 4 | Login válido | `200` + JWT |
| 5 | `GET /api/citas` sin token | `403` |
| 6 | `POST /api/veterinarios` con `USER` | `403 Forbidden` |
| 7 | Crear veterinario con `ADMIN` | `201` |
| 8 | Crear propietario | `201` |
| 9 | Crear mascota con propietario existente | `201` |
| 10 | Mascota con propietario inexistente | `400`, propietario no encontrado |
| 11 | Cita futura con referencias válidas | `201` |
| 12 | Cita con fecha pasada | `400` |
| 13 | Repetir veterinario y fecha/hora de una cita | `400` |
| 14 | Filtrar citas por veterinario | `200` con JWT y `veterinarioId=3` |
| 15 | Reiniciar; Swagger, Authorize y ruta protegida | Correcto |


## Nota sobre el uso de IA

En esta actividad se uso la IA para consultas, organizacion de datos, pruebas del funcionamiento, llevar un registro de lo que se realizo, explicaciones de codigo aun no entendido, recomendaciones de mejoras del codigo.