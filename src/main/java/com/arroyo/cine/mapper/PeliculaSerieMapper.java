package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PersonaMapper.class)
public interface PeliculaSerieMapper {

    @Mapping(target = "id_pelicula_serie", source = "peliculaSerie.id_pelicula_serie")
    @Mapping(target = "id_genero", source = "peliculaSerie.id_genero")
    @Mapping(target = "id_persona", source = "peliculaSerie.id_persona")
    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    @InheritInverseConfiguration
    @Mapping(target = "persona", ignore = true)
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
