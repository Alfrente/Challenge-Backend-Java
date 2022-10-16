package com.arroyo.cine.model.mapper.personaje;

import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PeliculaSerieComplementoMapper.class})
public interface PersonajeMapper {
    PersonajeDto aPersonajeDto(Personaje personaje);
    List<PersonajeDto> aListPersonajeDto(List<Personaje> personajes);
    @InheritInverseConfiguration
    Personaje aPersonaje(PersonajeDto personajeDto);
}
