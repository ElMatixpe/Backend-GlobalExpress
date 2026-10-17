package Minimarket.GlobalExpress_SpringBoot.security;

/** Datos del usuario que hizo la petición */
public record UsuarioSesion(Integer idUsuario, String username, String nombre, String rol) {
}
