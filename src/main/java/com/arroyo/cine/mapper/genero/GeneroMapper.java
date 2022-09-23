package com.arroyo.cine.mapper.genero;

import com.arroyo.cine.dto.genero.GeneroDto;
import com.arroyo.cine.entity.Genero;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    @Mapping(target = "ideGenero", source = "genero.idGenero")
    @Mapping(target = "nombreGenero", source = "genero.nombre")
    @Mapping(target = "imagenGenero", source = "genero.imagen")
    GeneroDto aGeneroDto(Genero genero);
    List<GeneroDto> aListGeneroDto(List<Genero> generos);

    @InheritInverseConfiguration
    Genero aGenero(GeneroDto generoDto);
}
