package mx.uv.dsw.api.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    public LocalDateTime timestamp = LocalDateTime.now();
    public int status;
    public String mensaje;

    public ErrorResponse(int status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }
}
