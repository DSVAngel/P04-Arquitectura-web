# PR01 - Web 3.0 - frontend-angular

Cliente Angular 16.2.12 (Node 18.20.8) que consume `api-spring`.

## RF08 cubierto
- **Catalogo** (`/catalogo`): consume `GET /api/productos`, muestra alerta de stock bajo, filtro "ver solo en alerta".
- **Movimientos** (`/movimientos`): consume `POST /api/movimientos` (operacion de cambio) y `GET /api/movimientos` (historial); **muestra los errores** que devuelve la API (ej. salida mayor a la existencia, campos obligatorios) en un mensaje visible con `role="alert"`.

## Ejecutar

1. Con `api-spring` corriendo en `http://localhost:8081` (ver su README).
2. Instalar dependencias y levantar el servidor de desarrollo:
   ```bash
   npm install
   npm start
   ```
3. Abrir `http://localhost:4200`.

`src/environments/environment.ts` define `apiUrl` (por defecto `http://localhost:8081/api`); ajustalo si cambias el puerto del API con `API_PORT`.

Requiere acceso a npm/Node para instalar Angular (no incluido en este paquete de codigo fuente).

## Pruebas minimas sugeridas
- Positiva: abrir `/catalogo` y verificar que carga la lista desde la API.
- Positiva: registrar una entrada en `/movimientos` y ver que "Ultimos movimientos" se actualiza.
- Negativa: registrar una salida mayor a la existencia y verificar que el mensaje de error de la API se muestra en pantalla (sin recargar la app).
