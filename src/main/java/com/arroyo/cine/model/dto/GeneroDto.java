package com.arroyo.cine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record GeneroDto(@JsonInclude(JsonInclude.Include.NON_EMPTY) String idGenero,
                        @JsonInclude(JsonInclude.Include.NON_EMPTY) String nombre,
                        @JsonInclude(JsonInclude.Include.NON_EMPTY) String imagen,
                        @JsonInclude(JsonInclude.Include.NON_EMPTY)  List<PeliculaSerieDto> peliculaSerieList) {

}
