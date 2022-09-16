package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PeliculaSerieMapper.class)
public interface PersonajeMapper {

    PersonajeDto aPersonajeDto(Personaje personaje);

    List<PersonajeDto> aListPersonajeDto(List<Personaje> personajes);

    @InheritInverseConfiguration
    @Mapping(target = "peliculaSeries", ignore = true)
    Personaje aPersonaje(PersonajeDto personajeDto);
}
