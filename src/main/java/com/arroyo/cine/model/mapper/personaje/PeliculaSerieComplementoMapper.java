package com.arroyo.cine.model.mapper.personaje;

import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSerieComplementoMapper {
    @Mapping(target = "personajes", ignore = true)
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);
    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);
}
