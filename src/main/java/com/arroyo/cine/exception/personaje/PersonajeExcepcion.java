package com.arroyo.cine.exception.personaje;

import com.arroyo.cine.exception.ExcepcionGenerica;
import org.springframework.http.HttpStatus;

public class PersonajeExcepcion extends ExcepcionGenerica {
    public PersonajeExcepcion(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, String mensaje, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensaje, codigo);
    }
}
