package com.arroyo.cine.exception.genero;

import com.arroyo.cine.exception.ExcepcionesGenerica;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GeneroExcepciones extends ExcepcionesGenerica {
    public GeneroExcepciones(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, List<String> mensajes, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensajes, codigo);
    }
}
