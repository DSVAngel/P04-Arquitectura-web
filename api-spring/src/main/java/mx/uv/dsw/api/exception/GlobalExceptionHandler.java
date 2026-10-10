package mx.uv.dsw.api.exception;

import mx.uv.dsw.api.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

/**
 * CA03: la API siempre devuelve JSON coherente, con 2xx en exito y 4xx en
 * entrada o regla de negocio invalida (nunca una pagina de error HTML).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovimientoInvalidoException.class)
    public ResponseEntity<ErrorResponse> manejarMovimientoInvalido(MovimientoInvalidoException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException e) {
        String mensaje = e.getBindingResult().getFieldErrors().isEmpty()
                ? "Datos invalidos"
                : e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(new ErrorResponse(400, mensaje));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGenerico(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Error interno del servidor."));
    }
}
