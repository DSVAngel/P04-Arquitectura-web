package mx.uv.dsw.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PR01. Control de inventario inteligente - Web 3.0.
 * API REST que expone el catalogo y los movimientos de inventario para el
 * cliente Angular, reutilizando el mismo esquema PostgreSQL de Web 1.0/2.0.
 */
@SpringBootApplication
public class ApiSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiSpringApplication.class, args);
    }
}
