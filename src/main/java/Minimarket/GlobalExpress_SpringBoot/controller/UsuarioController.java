package Minimarket.GlobalExpress_SpringBoot.controller;

import Minimarket.GlobalExpress_SpringBoot.dto.LoginRequest;
import Minimarket.GlobalExpress_SpringBoot.model.Usuario;
import Minimarket.GlobalExpress_SpringBoot.service.UsuarioService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Integer id) {
        return usuarioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }

    /** Login del React: devuelve el usuario (sin contraseña) o 401. */
    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest credenciales) {
        return usuarioService.autenticar(credenciales.username(), credenciales.contrasena());
    }
}
