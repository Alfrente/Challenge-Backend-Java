package com.arroyo.cine.mapper.pelicula_serie;

import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonajeComplementoPeliculaSerieMapper {
    @Mapping(target = "peliculaSeries", ignore = true)
    PersonajeDto aPersonajeDto(Personaje personaje);
    List<PersonajeDto> aListPersonajeDto(List<Personaje> personajes);
}
