package com.arroyo.cine.exception.custom.personaje;

import com.arroyo.cine.exception.custom.ExcepcionGenerica;
import org.springframework.http.HttpStatus;

public class PersonajeExcepcion extends ExcepcionGenerica {
    public PersonajeExcepcion(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, String mensaje, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensaje, codigo);
    }
}
