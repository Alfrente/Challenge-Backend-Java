package com.arroyo.cine.model.mapper.pelicula_serie;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonajeComplementoPeliculaSerieMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PeliculaSerieMapper {
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);
    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);
    @InheritInverseConfiguration
    @Mapping(target = "generoPelicula", ignore = true)
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
