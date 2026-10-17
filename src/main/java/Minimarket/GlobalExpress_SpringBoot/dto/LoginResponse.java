package Minimarket.GlobalExpress_SpringBoot.dto;

import Minimarket.GlobalExpress_SpringBoot.model.Usuario;

/** Respuesta del login: el token que el React debe enviar en cada petición y los datos del usuario. */
public record LoginResponse(String token, Usuario usuario) {
}
