package com.arroyo.cine.exception.pelicula.serie;

import com.arroyo.cine.exception.ExcepcionGenerica;
import org.springframework.http.HttpStatus;

public class PeliculaSerieExcepcion extends ExcepcionGenerica {
    public PeliculaSerieExcepcion(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, String mensaje, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensaje, codigo);
    }
}
