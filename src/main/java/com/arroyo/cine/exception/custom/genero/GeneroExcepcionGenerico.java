package com.arroyo.cine.exception.custom.genero;

import org.springframework.http.HttpStatus;

public class GeneroExcepcionGenerico extends RuntimeException{
    private String mensaje;
    private HttpStatus codigo;

    public GeneroExcepcionGenerico() {
    }

    public GeneroExcepcionGenerico(String mensaje, HttpStatus codigo) {
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
