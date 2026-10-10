package mx.uv.dsw.api.repository;

import mx.uv.dsw.api.model.Movimiento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findAllByOrderByFechaHoraDesc(Pageable pageable);
}
