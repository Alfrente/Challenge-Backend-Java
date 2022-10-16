package com.arroyo.cine.model.dto;

import com.arroyo.cine.model.entity.PeliculaSerie;

import java.util.List;

public record GeneroDtoTrcord(String idGenero, String nombre, String imagen, List<PeliculaSerie> peliculaSeries) {

    public GeneroDtoTrcord(String nombre, String imagen, List<PeliculaSerie> peliculaSeries) {
        this(null, nombre, imagen, peliculaSeries);
    }
}
