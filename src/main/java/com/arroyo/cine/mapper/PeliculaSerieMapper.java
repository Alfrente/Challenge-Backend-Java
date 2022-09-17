package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSerieMapper {

    @Mapping(target = "idPeliculaSerie", source = "peliculaSerie.idPeliculaSerie")
    @Mapping(target = "tituloPeliSerie", source = "peliculaSerie.titulo")
    @Mapping(target = "caratula", source = "peliculaSerie.imagen")
    @Mapping(target = "fechaCreacionPeliSerie", source = "peliculaSerie.fechaCreacion")
    @Mapping(target = "califiacionPeliSerie", source = "peliculaSerie.califiacion")
    @Mapping(target = "idPersonajePeliSerie", source = "peliculaSerie.idPersonaje")
    @Mapping(target = "idGeneroPeliSerie", source = "peliculaSerie.idGenero")
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    List<PeliculaSerieDto> aaPeliculaSerieDtoList(List<PeliculaSerie> peliculaSeries);

    @InheritInverseConfiguration
    @Mapping(target = "personaje", ignore = true)
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
