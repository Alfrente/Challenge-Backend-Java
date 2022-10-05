package com.arroyo.cine.exception.custom.pelicula.serie;

import org.springframework.http.HttpStatus;

import java.util.List;

public class PersonajeExcepciones extends RuntimeException {
    private List<String> mensajes;
    private HttpStatus codigo;

    public PersonajeExcepciones() {
    }

    public PersonajeExcepciones(List<String> mensajes, HttpStatus codigo) {
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
