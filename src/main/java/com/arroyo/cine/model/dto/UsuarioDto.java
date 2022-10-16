package com.arroyo.cine.model.dto;

public record UsuarioDto(Integer idUsuario, String usuario, String correo, String contrasena, String rol) {
    public UsuarioDto(String usuario, String correo, String contrasena, String rol) {
        this(null, usuario, correo, contrasena, rol);
    }
}
