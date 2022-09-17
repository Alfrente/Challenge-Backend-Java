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
    @Mapping(target = "nombrePer", source = "personaje.nombre")
    @Mapping(target = "edadPer", source = "personaje.edad")
    @Mapping(target = "pesoPer", source = "personaje.peso")
    @Mapping(target = "imagenPer", source = "personaje.imagen")
    @Mapping(target = "historiaPer", source = "personaje.historia")
    @Mapping(target = "peliculaSeries", source = "personaje.peliculaSeries")
    PersonajeDto aPersonajeDto(Personaje personaje);

    List<PersonajeDto> aListPersonajeDto(List<Personaje> personajes);

    @InheritInverseConfiguration
    @Mapping(target = "peliculaSeries", ignore = true)
    Personaje aPersonaje(PersonajeDto personajeDto);
}
