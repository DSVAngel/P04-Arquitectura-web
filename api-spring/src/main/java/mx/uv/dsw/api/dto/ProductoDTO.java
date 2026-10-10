package mx.uv.dsw.api.dto;

import mx.uv.dsw.api.model.Producto;

import java.math.BigDecimal;

/** Representacion expuesta por la API; evita serializar la entidad JPA directamente. */
public class ProductoDTO {
    public Integer idProducto;
    public String codigo;
    public String nombre;
    public String nombreCategoria;
    public String unidadMedida;
    public BigDecimal minimoPermitido;
    public BigDecimal existencia;
    public boolean alertaStockBajo;

    public static ProductoDTO desde(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.idProducto = p.getIdProducto();
        dto.codigo = p.getCodigo();
        dto.nombre = p.getNombre();
        dto.nombreCategoria = p.getCategoria() != null ? p.getCategoria().getNombre() : null;
        dto.unidadMedida = p.getUnidadMedida();
        dto.minimoPermitido = p.getMinimoPermitido();
        dto.existencia = p.getExistencia();
        dto.alertaStockBajo = p.isAlertaStockBajo();
        return dto;
    }
}
