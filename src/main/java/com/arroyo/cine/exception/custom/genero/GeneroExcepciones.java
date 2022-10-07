package com.arroyo.cine.exception.custom.genero;

import com.arroyo.cine.exception.custom.ExcepcionesGenerica;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GeneroExcepciones extends ExcepcionesGenerica {
    public GeneroExcepciones(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, List<String> mensajes, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensajes, codigo);
    }
}
