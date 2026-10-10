package mx.uv.dsw.api.web;

import mx.uv.dsw.api.dto.MovimientoDTO;
import mx.uv.dsw.api.dto.NuevoMovimientoRequest;
import mx.uv.dsw.api.model.Movimiento;
import mx.uv.dsw.api.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RF02/RF03/RF04 en Web 3.0: registrar entradas/salidas y consultar el
 * historial reciente. RF08: al menos una operacion de cambio del flujo
 * principal expuesta por API.
 */
@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<MovimientoDTO> listarRecientes(@RequestParam(name = "limite", defaultValue = "10") int limite) {
        return movimientoService.listarRecientes(limite).stream()
                .map(MovimientoDTO::desde)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<MovimientoDTO> registrar(@Valid @RequestBody NuevoMovimientoRequest request) {
        Movimiento movimiento = movimientoService.registrar(
                request.idProducto, request.idUsuario, request.tipo, request.cantidad, request.comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(MovimientoDTO.desde(movimiento));
    }
}
