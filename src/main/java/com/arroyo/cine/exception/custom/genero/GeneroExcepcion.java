package com.arroyo.cine.exception.custom.genero;

import com.arroyo.cine.exception.custom.ExcepcionGenerica;
import org.springframework.http.HttpStatus;

public class GeneroExcepcion extends ExcepcionGenerica{
    public GeneroExcepcion(String llaveMapMensajeError, String llaveMapMensajeErrorCodigo, String mensaje, HttpStatus codigo) {
        super(llaveMapMensajeError, llaveMapMensajeErrorCodigo, mensaje, codigo);
    }
}
