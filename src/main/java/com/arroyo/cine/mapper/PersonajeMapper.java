package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PeliculaSerieMapper.class})
public interface PersonajeMapper {

    @Mapping(target = "idePersonaje", source = "personaje.idPersonaje")
    @Mapping(target = "nombre", source = "personaje.nombre")
    @Mapping(target = "edad", source = "personaje.edad")
    @Mapping(target = "peso", source = "personaje.peso")
    @Mapping(target = "imagen", source = "personaje.imagen")
    @Mapping(target = "historia", source = "personaje.historia")
    @Mapping(target = "peliculaSeries", source = "personaje.peliculaSeries")
    PersonajeDto aPersonajeDto(Personaje personaje);

    List<PersonajeDto> aListPersonajeDto(List<Personaje> personajes);

    @InheritInverseConfiguration
    @Mapping(target = "peliculaSeries", ignore = true)
    Personaje aPersonaje(PersonajeDto personajeDto);
}
