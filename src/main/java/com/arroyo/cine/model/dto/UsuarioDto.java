package com.arroyo.cine.model.dto;

import javax.validation.constraints.Null;

public class UsuarioDto {

    Integer idUsuario;

    @Null
    String usuario;

    @Null
    String correo;

    @Null
    String contrasena;

    @Null
    String rol;

    public UsuarioDto() {
    }

    public UsuarioDto(String usuario, String correo, String contrasena, String rol) {
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
