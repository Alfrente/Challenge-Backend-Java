package com.arroyo.cine.mapper.pelicula_serie;

import com.arroyo.cine.dto.personaje.PeliculaSeriePersolizadaDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSeriePersonalizadoMapper {
    @Mapping(target = "titulo", source = "peliculaSerie.titulo")
    @Mapping(target = "imagen", source = "peliculaSerie.imagen")
    @Mapping(target = "fechaCreacion", source = "peliculaSerie.fechaCreacion")
    PeliculaSeriePersolizadaDto aPeliculaSeriePersolizadaDto(PeliculaSerie peliculaSerie);

    List<PeliculaSeriePersolizadaDto> aListPeliculaSeriePersolizadaDto(List<PeliculaSerie> peliculaSeries);
}
