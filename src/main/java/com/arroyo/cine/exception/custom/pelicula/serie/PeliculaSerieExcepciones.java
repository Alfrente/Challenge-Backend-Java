package com.arroyo.cine.exception.custom.pelicula.serie;

import com.arroyo.cine.exception.custom.ExcepcionesGenerica;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PeliculaSerieExcepciones extends ExcepcionesGenerica {
    public PeliculaSerieExcepciones(List<String> mensajes, HttpStatus codigo) {
        super(mensajes, codigo);
    }
}

