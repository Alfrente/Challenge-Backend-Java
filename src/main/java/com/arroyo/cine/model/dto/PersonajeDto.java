package com.arroyo.cine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record PersonajeDto(@JsonInclude(JsonInclude.Include.NON_EMPTY) String idPersonaje,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) String nombre,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) String edad,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) String peso,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) String imagen,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) String historia,
                           @JsonInclude(JsonInclude.Include.NON_EMPTY) List<PeliculaSerieDto> peliculaSeries) {

}
