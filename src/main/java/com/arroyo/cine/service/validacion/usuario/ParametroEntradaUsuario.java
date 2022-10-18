package com.arroyo.cine.service.validacion.usuario;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.model.dto.UsuarioDto;
import org.springframework.http.HttpStatus;

import static com.arroyo.cine.util.statico.ExprecionRegular.CORREO_GMAIL;
import static com.arroyo.cine.util.statico.ExprecionRegular.TEXTO_CON_ESPACIOS_NUMERO;
import static com.arroyo.cine.util.statico.MensajeError.*;

public class ParametroEntradaUsuario {
    private ParametroEntradaUsuario() {
    }

    public static void validarUsuarioDto(UsuarioDto dto) {
        if (dto != null && dto.idUsuario() == null
                && dto.usuario() == null && dto.contrasena() == null
                && dto.correo() == null && dto.rol() == null
        )
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarCorreo(String correo) {
        if (correo == null || !correo.matches(CORREO_GMAIL))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_INGRESE + "un correo" + VALIDO, HttpStatus.BAD_REQUEST);
    }

    public static void validarUsuario(String usuario) {
        if (usuario == null || usuario.matches(TEXTO_CON_ESPACIOS_NUMERO))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_INGRESE + "un usuario" + VALIDO, HttpStatus.BAD_REQUEST);
    }

    public static void validarRol(String rol) {
        if (!rolValido(rol))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_INGRESE + "el rol" + VALIDO, HttpStatus.BAD_REQUEST);
    }

    private static boolean rolValido(String rol) {
        return (rol.equals("ROLE_ADMIN") || rol.equals("ROLE_USER") || rol.equals("ROLE_ADMIN,ROLE_USER") || rol.equals("ROLE_USER,ROLE_ADMIN"));
    }
}
