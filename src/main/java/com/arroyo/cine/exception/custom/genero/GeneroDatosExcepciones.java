package com.arroyo.cine.exception.custom.genero;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class GeneroDatosExcepciones extends RuntimeException{
    private List<String> mensajes;
    private LocalDateTime fecha;
    private HttpStatus codigo;

    public GeneroDatosExcepciones() {
    }

    public GeneroDatosExcepciones(List<String> mensajes, HttpStatus codigo) {
        this.mensajes = mensajes;
        this.fecha = LocalDateTime.now();
        this.codigo = codigo;
    }

    public void setMensajes(List<String> mensaje) {
        this.mensajes = mensaje;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }
}
