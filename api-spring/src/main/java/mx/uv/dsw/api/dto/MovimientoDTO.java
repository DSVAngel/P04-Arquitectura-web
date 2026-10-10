package mx.uv.dsw.api.dto;

import mx.uv.dsw.api.model.Movimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoDTO {
    public Integer idMovimiento;
    public Integer idProducto;
    public String nombreProducto;
    public Integer idUsuario;
    public String nombreUsuario;
    public String tipo;
    public BigDecimal cantidad;
    public LocalDateTime fechaHora;
    public String comentario;
    public BigDecimal existenciaResultante;

    public static MovimientoDTO desde(Movimiento m) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.idMovimiento = m.getIdMovimiento();
        dto.idProducto = m.getProducto().getIdProducto();
        dto.nombreProducto = m.getProducto().getNombre();
        dto.idUsuario = m.getUsuario().getIdUsuario();
        dto.nombreUsuario = m.getUsuario().getNombre();
        dto.tipo = m.getTipo();
        dto.cantidad = m.getCantidad();
        dto.fechaHora = m.getFechaHora();
        dto.comentario = m.getComentario();
        dto.existenciaResultante = m.getProducto().getExistencia();
        return dto;
    }
}
