package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PersonaDto;
import com.arroyo.cine.entity.Persona;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PeliculaSerieMapper.class)
public interface PersonaMapper {
    @Mapping(target = "persona", source = "persona.id_persona")
    PersonaDto aPersonaDto(Persona persona);

    @InheritInverseConfiguration
    @Mapping(target = "peliculaSeries", ignore = true)
    Persona aPersona(PersonaDto personaDto);
}
