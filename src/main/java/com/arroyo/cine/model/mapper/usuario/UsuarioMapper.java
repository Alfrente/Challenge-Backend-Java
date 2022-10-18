package com.arroyo.cine.model.mapper.usuario;

import com.arroyo.cine.model.dto.UsuarioDto;
import com.arroyo.cine.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "nombreUsuario", source = "dto.usuario")
    Usuario aUsuario(UsuarioDto dto);
}
