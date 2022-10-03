package com.arroyo.cine.exception.custom.genero;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class GeneroExcepcionGenerico extends RuntimeException{
    private String mensaje;
    private LocalDateTime fecha;
    private HttpStatus codigo;

    public GeneroExcepcionGenerico() {
    }

    public GeneroExcepcionGenerico(String mensaje, HttpStatus codigo) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }
}
