package mx.uv.dsw.api.web;

import mx.uv.dsw.api.dto.ProductoDTO;
import mx.uv.dsw.api.model.Producto;
import mx.uv.dsw.api.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RF01/RF06 en Web 3.0: catalogo y alertas expuestos como API para el
 * cliente Angular. RF08: al menos una consulta expuesta por API.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public List<ProductoDTO> listar(@RequestParam(name = "alerta", required = false, defaultValue = "false") boolean soloAlerta) {
        List<Producto> productos = soloAlerta ? productoRepository.buscarEnAlerta() : productoRepository.findByActivoTrueOrderByNombre();
        return productos.stream().map(ProductoDTO::desde).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El producto " + id + " no existe."));
        return ProductoDTO.desde(producto);
    }
}
