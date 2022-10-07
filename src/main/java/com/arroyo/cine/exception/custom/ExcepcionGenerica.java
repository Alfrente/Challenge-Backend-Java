package com.arroyo.cine.exception.custom;

import org.springframework.http.HttpStatus;

public class ExcepcionGenerica extends RuntimeException{
    private String mensaje;
    private HttpStatus codigo;

    public ExcepcionGenerica() {
    }

    public ExcepcionGenerica(String mensaje, HttpStatus codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }
}
