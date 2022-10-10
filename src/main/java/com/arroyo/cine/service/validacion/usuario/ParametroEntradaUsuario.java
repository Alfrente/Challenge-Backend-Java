package com.arroyo.cine.service.validacion.usuario;

import com.arroyo.cine.exception.ExcepcionGenerica;
import com.arroyo.cine.model.dto.UsuarioDto;
import org.springframework.http.HttpStatus;

public class ParametroEntradaUsuario {

    private void validarUsuarioDto(UsuarioDto dto) {
        if (dto != null && dto.getIdUsuario() == null
                && dto.getNombreUsuario() == null && dto.getContrasena() == null
                && dto.getCorreo() == null && dto.getRol() == null
        )
            throw new ExcepcionGenerica("", "", "", HttpStatus.BAD_REQUEST);
    }
}
