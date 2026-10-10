# PR01 - Web 3.0 - api-spring

API REST (Spring Boot 2.7.18, Java 11) que expone el catalogo y los
movimientos de inventario de PR01 para el cliente Angular, reutilizando el
**mismo esquema PostgreSQL** ya creado en el incremento Web 1.0/2.0
(`sql/schema.sql`: tablas `producto`, `movimiento`, `categoria`, `usuario`).

## RF08 cubierto
- **Consulta**: `GET /api/productos` (con filtro `?alerta=true`), `GET /api/productos/{id}`.
- **Operacion de cambio del flujo principal**: `POST /api/movimientos` (registra entrada/salida y actualiza la existencia).
- `GET /api/movimientos` para el historial reciente, `GET /api/categorias` y `GET /api/usuarios` como catalogos de apoyo para los formularios.
- `GET /api/health` como ruta de salud.

## Regla de negocio (mismo riesgo/control que Web 1.0/2.0)
`MovimientoService.registrar` usa bloqueo pesimista (`SELECT ... FOR UPDATE` via JPA) y rechaza con **400** cualquier salida mayor a la existencia disponible o un usuario sin rol autorizado (`ALMACEN`/`CAPTURA`). Nunca deja existencias negativas.

## CA03 (estados HTTP coherentes)
`GlobalExceptionHandler` traduce:
- Regla de negocio invalida o datos invalidos → **400** con `{ "mensaje": "..." }`.
- Producto/usuario no encontrado → **404**.
- Exito → **200** (consultas) o **201** (`POST /api/movimientos`).

## Ejecutar

1. Requiere el esquema ya cargado (`sql/schema.sql` del incremento Web 1.0).
2. Variables de entorno (RNF02, sin credenciales en el codigo):
   ```bash
   export DB_URL=jdbc:postgresql://localhost:5432/pr01_inventario
   export DB_USER=postgres
   export DB_PASSWORD=tu_password
   export ANGULAR_ORIGIN=http://localhost:4200   # opcional, default ya es este
   ```
3. Compilar y ejecutar:
   ```bash
   mvn clean spring-boot:run
   ```
   La API queda en `http://localhost:8081/api` (puerto configurable con `API_PORT`).

Requiere acceso a Maven Central para descargar Spring Boot y el driver de PostgreSQL (no incluidos en este paquete de codigo fuente).

## Pruebas minimas sugeridas
- Positiva: `GET /api/productos` responde 200 con la lista.
- Positiva: `POST /api/movimientos` con una entrada valida responde 201 y la existencia aumenta.
- Negativa: `POST /api/movimientos` con una salida mayor a la existencia responde 400 con el mensaje de la regla de negocio.
- Negativa: `POST /api/movimientos` con `cantidad: 0` responde 400 (validacion `@DecimalMin`).
