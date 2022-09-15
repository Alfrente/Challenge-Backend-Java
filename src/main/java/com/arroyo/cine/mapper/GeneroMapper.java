package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.GeneroDto;
import com.arroyo.cine.entity.Genero;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
    @Mapping(target = "genero", source = "genero.id_genero")
    @Mapping(target = "persona", source = "genero.id_persona")
    GeneroDto aGeneroDto(Genero genero);
    List<GeneroDto> aListGeneroDto(List<Genero> generos);

    @InheritInverseConfiguration
    Genero aGenero(GeneroDto generoDto);
}
