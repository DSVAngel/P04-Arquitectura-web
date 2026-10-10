package mx.uv.dsw.api.exception;

/** Se lanza cuando una regla de negocio del movimiento no se cumple (ej. salida mayor a la existencia). */
public class MovimientoInvalidoException extends RuntimeException {
    public MovimientoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
