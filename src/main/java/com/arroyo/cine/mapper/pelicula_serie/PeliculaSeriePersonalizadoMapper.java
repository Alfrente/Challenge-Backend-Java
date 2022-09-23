package com.arroyo.cine.mapper.pelicula_serie;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSeriePersonalizadoPsDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSeriePersonalizadoMapper {
    @Mapping(target = "titulo", source = "peliculaSerie.titulo")
    @Mapping(target = "caratula", source = "peliculaSerie.imagen")
    @Mapping(target = "fechaCreacion", source = "peliculaSerie.fechaCreacion")
    @Mapping(target = "personajes", source = "peliculaSerie.personajes")
    PeliculaSeriePersonalizadoPsDto aPeliculaSeriePersolizadaDto(PeliculaSerie peliculaSerie);

    List<PeliculaSeriePersonalizadoPsDto> aListPeliculaSeriePersolizadaDto(List<PeliculaSerie> peliculaSeries);
}
