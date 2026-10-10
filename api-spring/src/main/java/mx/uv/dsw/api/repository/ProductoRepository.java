package mx.uv.dsw.api.repository;

import mx.uv.dsw.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByActivoTrueOrderByNombre();

    @Query("SELECT p FROM Producto p WHERE p.activo = true AND p.existencia < p.minimoPermitido ORDER BY p.nombre")
    List<Producto> buscarEnAlerta();

    /** Bloqueo pesimista: evita condiciones de carrera al ajustar la existencia (RNF04). */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Producto p WHERE p.idProducto = :id")
    Optional<Producto> buscarParaActualizar(Integer id);
}
