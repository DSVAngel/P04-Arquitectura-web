package mx.uv.dsw.api.web;

import mx.uv.dsw.api.model.Categoria;
import mx.uv.dsw.api.model.Usuario;
import mx.uv.dsw.api.repository.CategoriaRepository;
import mx.uv.dsw.api.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Catalogos de apoyo para poblar los formularios del cliente Angular. */
@RestController
@RequestMapping("/api")
public class CatalogoAuxiliarController {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CatalogoAuxiliarController(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
