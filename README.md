# PR01. Control de inventario inteligente - incremento Web 3.0

Este paquete contiene **solo** el incremento Web 3.0 (API REST + cliente Angular):

```
dsw-evolucion-web/
  api-spring/        API REST Java 11 / Spring Boot 2.7.18
  frontend-angular/  Cliente Angular 16.2.12
```

Se integra junto a los incrementos ya entregados de Web 1.0 (`web1-jsp/`) y Web 2.0
(`web2-jsf/`), que comparten el mismo esquema PostgreSQL (`sql/schema.sql`:
`producto`, `movimiento`, `categoria`, `usuario`, `lectura_sensor` reservada
para Web 4.0). Coloca estas dos carpetas dentro de tu `codigo/dsw-evolucion-web/`
junto a `web1-jsp/` y `web2-jsf/`.

Ver el README de cada modulo (`api-spring/README.md`, `frontend-angular/README.md`)
para variables de entorno y pasos de ejecucion.

## Orden de arranque
1. PostgreSQL con el esquema ya cargado.
2. `api-spring` (puerto 8081 por defecto).
3. `frontend-angular` (puerto 4200 por defecto, apunta a `api-spring`).

## Alcance de esta entrega
RF08 (API + Angular consumiendo consulta y operacion de cambio) y CA03
(estados HTTP 2xx/4xx coherentes) de la ficha PR01. **No** incluye RF09 (evento
IoT simulado, Web 4.0) — ese es el siguiente incremento.
