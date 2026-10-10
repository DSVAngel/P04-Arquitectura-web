package mx.uv.dsw.api.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(nullable = false, length = 120)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;

    @Column(name = "minimo_permitido", nullable = false, precision = 12, scale = 2)
    private BigDecimal minimoPermitido = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal existencia = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean activo = true;

    /** RF06: alerta cuando la existencia cae por debajo del minimo permitido. */
    @Transient
    public boolean isAlertaStockBajo() {
        return existencia != null && minimoPermitido != null && existencia.compareTo(minimoPermitido) < 0;
    }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
    public BigDecimal getMinimoPermitido() { return minimoPermitido; }
    public void setMinimoPermitido(BigDecimal minimoPermitido) { this.minimoPermitido = minimoPermitido; }
    public BigDecimal getExistencia() { return existencia; }
    public void setExistencia(BigDecimal existencia) { this.existencia = existencia; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
