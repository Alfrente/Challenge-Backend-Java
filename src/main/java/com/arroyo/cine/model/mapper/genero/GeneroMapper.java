package com.arroyo.cine.model.mapper.genero;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.model.entity.Genero;
import com.arroyo.cine.model.mapper.personaje.PeliculaSerieComplementoMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PeliculaSerieComplementoMapper.class})
public interface GeneroMapper {
    GeneroDto aGeneroDto(Genero genero);
    List<GeneroDto> aListGeneroDto(List<Genero> generos);
    @InheritInverseConfiguration
    @Mapping(target = "peliculaSerieList", ignore = true)
    Genero aGenero(GeneroDto generoDto);
}
