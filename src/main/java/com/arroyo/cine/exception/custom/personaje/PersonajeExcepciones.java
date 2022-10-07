package com.arroyo.cine.exception.custom.personaje;

import com.arroyo.cine.exception.custom.ExcepcionesGenerica;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PersonajeExcepciones extends ExcepcionesGenerica {
    public PersonajeExcepciones(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, List<String> mensajes, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensajes, codigo);
    }
}