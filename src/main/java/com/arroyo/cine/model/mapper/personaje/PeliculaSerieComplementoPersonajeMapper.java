package com.arroyo.cine.model.mapper.personaje;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSerieComplementoPersonajeMapper {
    @Mapping(target = "personajes", ignore = true)
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);

    @InheritInverseConfiguration
    @Mapping(target = "personajes", ignore = true)
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
