package com.arroyo.cine.exception.custom.pelicula.serie;

import com.arroyo.cine.exception.custom.ExcepcionesGenerica;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PeliculaSerieExcepciones extends ExcepcionesGenerica {
    public PeliculaSerieExcepciones(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, List<String> mensajes, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensajes, codigo);
    }
}

