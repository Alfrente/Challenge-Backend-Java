package com.arroyo.cine.exception.custom.personaje;

import org.springframework.http.HttpStatus;

public class PersonajeExcepcionGenerico extends RuntimeException {
    private String mensaje;
    private HttpStatus codigo;

    public PersonajeExcepcionGenerico() {
    }

    public PersonajeExcepcionGenerico(String mensaje, HttpStatus codigo) {
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
