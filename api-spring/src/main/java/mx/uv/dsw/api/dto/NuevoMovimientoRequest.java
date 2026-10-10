package mx.uv.dsw.api.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class NuevoMovimientoRequest {

    @NotNull(message = "idProducto es obligatorio")
    public Integer idProducto;

    @NotNull(message = "idUsuario es obligatorio")
    public Integer idUsuario;

    @NotNull(message = "tipo es obligatorio")
    @Pattern(regexp = "ENTRADA|SALIDA", message = "tipo debe ser ENTRADA o SALIDA")
    public String tipo;

    @NotNull(message = "cantidad es obligatoria")
    @DecimalMin(value = "0.01", message = "cantidad debe ser mayor que cero")
    public BigDecimal cantidad;

    public String comentario;
}
