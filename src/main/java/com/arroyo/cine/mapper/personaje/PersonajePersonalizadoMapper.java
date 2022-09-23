package com.arroyo.cine.mapper.personaje;

import com.arroyo.cine.dto.personaje.PersonajePersonalizadoDto;
import com.arroyo.cine.entity.Personaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PeliculaSerieComplementoPMapper.class})
public interface PersonajePersonalizadoMapper {

    @Mapping(target = "nombre", source = "personaje.nombre")
    @Mapping(target = "edad", source = "personaje.edad")
    @Mapping(target = "peso", source = "personaje.peso")
    @Mapping(target = "imagen", source = "personaje.imagen")
    @Mapping(target = "historia", source = "personaje.historia")
    @Mapping(target = "peliculaSeries", source = "personaje.peliculaSeries")
    PersonajePersonalizadoDto aPersonajeDto(Personaje personaje);

    List<PersonajePersonalizadoDto> aListPersonajeDto(List<Personaje> personajes);
}
