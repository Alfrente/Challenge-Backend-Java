package com.arroyo.cine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record PeliculaSerieDto(@JsonInclude(JsonInclude.Include.NON_EMPTY) String idPeliculaSerie,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) String titulo,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) String imagen,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) String fechaCreacion,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) String califiacion,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) String idGenero,
                               @JsonInclude(JsonInclude.Include.NON_EMPTY) List<PersonajeDto> personajes) {

}
