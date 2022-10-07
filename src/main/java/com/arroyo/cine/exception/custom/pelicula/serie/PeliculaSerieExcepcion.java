package com.arroyo.cine.exception.custom.pelicula.serie;

import com.arroyo.cine.exception.custom.ExcepcionGenerica;
import org.springframework.http.HttpStatus;

public class PeliculaSerieExcepcion extends ExcepcionGenerica {
    public PeliculaSerieExcepcion(String mensaje, HttpStatus codigo) {
        super(mensaje, codigo);
    }
}
