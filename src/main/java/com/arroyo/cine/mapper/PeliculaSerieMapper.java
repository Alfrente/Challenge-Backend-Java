package com.arroyo.cine.mapper;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaSerieMapper {

    PeliculaSerieDto aPeliculaSerieDto(PeliculaSerie peliculaSerie);

    List<PeliculaSerieDto> aaPeliculaSerieDtoList(List<PeliculaSerie> peliculaSeries);

    @InheritInverseConfiguration
    @Mapping(target = "personaje", ignore = true)
    PeliculaSerie aPeliculaSerie(PeliculaSerieDto peliculaSerieDto);
}
