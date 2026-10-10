-- PR01. Control de inventario inteligente - esquema PostgreSQL compartido
-- por los incrementos Web 1.0 (web1-jsp), Web 2.0 (web2-jsf) y Web 3.0
-- (api-spring / frontend-angular). spring.jpa.hibernate.ddl-auto=validate
-- en api-spring, por lo que este esquema debe coincidir exacto con las
-- entidades JPA en api-spring/src/main/java/mx/uv/dsw/api/model/.

CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    nombre       VARCHAR(80)  NOT NULL UNIQUE,
    descripcion  VARCHAR(255)
);

CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nombre     VARCHAR(120) NOT NULL,
    correo     VARCHAR(150) NOT NULL UNIQUE,
    rol        VARCHAR(30)  NOT NULL CHECK (rol IN ('ALMACEN', 'CAPTURA', 'COORDINACION')),
    activo     BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE producto (
    id_producto      SERIAL PRIMARY KEY,
    codigo           VARCHAR(30)   NOT NULL UNIQUE,
    nombre           VARCHAR(120)  NOT NULL,
    id_categoria     INTEGER       NOT NULL REFERENCES categoria(id_categoria),
    unidad_medida    VARCHAR(20)   NOT NULL,
    minimo_permitido NUMERIC(12,2) NOT NULL DEFAULT 0,
    existencia       NUMERIC(12,2) NOT NULL DEFAULT 0,
    activo           BOOLEAN       NOT NULL DEFAULT TRUE
);

CREATE TABLE movimiento (
    id_movimiento SERIAL PRIMARY KEY,
    id_producto   INTEGER       NOT NULL REFERENCES producto(id_producto),
    id_usuario    INTEGER       NOT NULL REFERENCES usuario(id_usuario),
    tipo          VARCHAR(10)   NOT NULL CHECK (tipo IN ('ENTRADA', 'SALIDA')),
    cantidad      NUMERIC(12,2) NOT NULL CHECK (cantidad > 0),
    fecha_hora    TIMESTAMP     NOT NULL DEFAULT NOW(),
    comentario    VARCHAR(255)
);

-- Reservada para el incremento Web 4.0 (RF09, evento IoT simulado).
-- No consumida por api-spring en este incremento Web 3.0.
CREATE TABLE lectura_sensor (
    id_lectura   SERIAL PRIMARY KEY,
    id_producto  INTEGER       NOT NULL REFERENCES producto(id_producto),
    valor        NUMERIC(12,2) NOT NULL,
    unidad       VARCHAR(20)   NOT NULL,
    fecha_hora   TIMESTAMP     NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_movimiento_producto ON movimiento(id_producto);
CREATE INDEX idx_movimiento_fecha ON movimiento(fecha_hora DESC);
