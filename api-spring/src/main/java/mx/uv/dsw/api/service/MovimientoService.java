package mx.uv.dsw.api.service;

import mx.uv.dsw.api.exception.MovimientoInvalidoException;
import mx.uv.dsw.api.model.Movimiento;
import mx.uv.dsw.api.model.Producto;
import mx.uv.dsw.api.model.Usuario;
import mx.uv.dsw.api.repository.MovimientoRepository;
import mx.uv.dsw.api.repository.ProductoRepository;
import mx.uv.dsw.api.repository.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Riesgo principal de la ficha PR01: alteracion de existencias. Se controla
 * con autorizacion por rol y una transaccion con bloqueo de fila que impide
 * existencias negativas, igual que en los DAO de Web 1.0/2.0.
 */
@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public MovimientoService(MovimientoRepository movimientoRepository,
                              ProductoRepository productoRepository,
                              UsuarioRepository usuarioRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private boolean puedeRegistrarMovimientos(Usuario usuario) {
        return usuario != null && usuario.isActivo()
                && ("ALMACEN".equals(usuario.getRol()) || "CAPTURA".equals(usuario.getRol()));
    }

    @Transactional
    public Movimiento registrar(Integer idProducto, Integer idUsuario, String tipo,
                                 BigDecimal cantidad, String comentario) {

        if (!"ENTRADA".equals(tipo) && !"SALIDA".equals(tipo)) {
            throw new MovimientoInvalidoException("Tipo de movimiento invalido.");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe."));
        if (!puedeRegistrarMovimientos(usuario)) {
            throw new MovimientoInvalidoException("El usuario no tiene autorizacion para registrar movimientos.");
        }

        // Bloqueo pesimista: dos movimientos concurrentes sobre el mismo
        // producto no pueden pisarse la existencia (RNF04).
        Producto producto = productoRepository.buscarParaActualizar(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("El producto no existe."));

        BigDecimal nuevaExistencia = "ENTRADA".equals(tipo)
                ? producto.getExistencia().add(cantidad)
                : producto.getExistencia().subtract(cantidad);

        if (nuevaExistencia.compareTo(BigDecimal.ZERO) < 0) {
            throw new MovimientoInvalidoException(
                    "No se puede registrar una salida mayor a la existencia disponible (" + producto.getExistencia() + ").");
        }

        producto.setExistencia(nuevaExistencia);
        productoRepository.save(producto);

        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setUsuario(usuario);
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);
        movimiento.setComentario(comentario);
        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> listarRecientes(int limite) {
        return movimientoRepository.findAllByOrderByFechaHoraDesc(PageRequest.of(0, limite));
    }
}
