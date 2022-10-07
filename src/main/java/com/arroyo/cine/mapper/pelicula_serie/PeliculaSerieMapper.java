package com.arroyo.cine.mapper.pelicula_serie;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonajeComplementoPeliculaSerieMapper.class})
public interface PeliculaSerieMapper {

    @Mapping(target = "idPeliculaSerie", source = "peliculaSerie.idPeliculaSerie")
    @Mapping(target = "titulo", source = "peliculaSerie.titulo")
    @Mapping(target = "caratula", source = "peliculaSerie.imagen")
    @Mapping(target = "fechaCreacion", source = "peliculaSerie.fechaCreacion")
    @Mapping(target = "califiacion", source = "peliculaSerie.califiacion")
    @Mapping(target = "idGenero", source = "peliculaSerie.idGenero")
    @Mapping(target = "personajes", source = "peliculaSerie.personajes")
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    List<PeliculaSerieDto> aListPeliculaSerieDto(List<PeliculaSerie> peliculaSeries);

    @InheritInverseConfiguration
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
