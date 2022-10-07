package com.arroyo.cine.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ExcepcionesGenerica extends RuntimeException{
    private List<String> mensajes;
    private HttpStatus codigo;

    public ExcepcionesGenerica() {
    }

    public ExcepcionesGenerica(List<String> mensajes, HttpStatus codigo) {
        this.mensajes = mensajes;
        this.codigo = codigo;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }
}
