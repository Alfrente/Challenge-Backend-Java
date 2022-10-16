package com.arroyo.cine.model.mapper.pelicula_serie;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonajeComplementoPeliculaSerieMapper.class})
public interface PeliculaSerieMapper {
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);
    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);
    @InheritInverseConfiguration
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
