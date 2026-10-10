package mx.uv.dsw.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Ruta de salud simple para comprobar el despliegue del backend Web 3.0. */
@RestController
public class HealthController {

    @GetMapping("/api/health")
    public String salud() {
        return "OK";
    }
}
