package com.arroyo.cine.exception.custom.genero;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GeneroExcepciones extends RuntimeException{
    private List<String> mensajes;
    private HttpStatus codigo;

    public GeneroExcepciones() {
    }

    public GeneroExcepciones(List<String> mensajes, HttpStatus codigo) {
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
