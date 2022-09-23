package com.arroyo.cine.mapper.pelicula_serie;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSerieComplementoPsMapper {

    @Mapping(target = "idPeliculaSerie", source = "peliculaSerie.idPeliculaSerie")
    @Mapping(target = "titulo", source = "peliculaSerie.titulo")
    @Mapping(target = "caratula", source = "peliculaSerie.imagen")
    @Mapping(target = "fechaCreacion", source = "peliculaSerie.fechaCreacion")
    @Mapping(target = "califiacion", source = "peliculaSerie.califiacion")
    @Mapping(target = "idPersonaje", source = "peliculaSerie.idPersonaje")
    @Mapping(target = "idGenero", source = "peliculaSerie.idGenero")
    @Mapping(target = "personajes", ignore = true)
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);
}
