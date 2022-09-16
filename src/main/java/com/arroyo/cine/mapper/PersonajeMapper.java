package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PersonaDto;
import com.arroyo.cine.entity.Personaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PeliculaSerieMapper.class)
public interface PersonaMapper {
    @Mapping(target = "persona", source = "personaje.id_personaje")
    PersonaDto aPersonaDto(Personaje personaje);

    @InheritInverseConfiguration
    @Mapping(target = "peliculaSeries", ignore = true)
    Personaje aPersona(PersonaDto personaDto);
}
