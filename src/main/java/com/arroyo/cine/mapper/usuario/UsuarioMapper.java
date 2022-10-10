package com.arroyo.cine.mapper.usuario;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.model.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario aUsuario(UsuarioDto dto);
    UsuarioDto aUsuarioDto(Usuario usuario);
}
