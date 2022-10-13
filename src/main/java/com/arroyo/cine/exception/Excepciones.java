package com.arroyo.cine.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Excepciones extends RuntimeException{
    private String llaveMapMensajeOErrorCodigo;
    private String llaveMapMensajeOError;
    private List<String> mensajes;
    private HttpStatus codigo;

    public Excepciones() {
    }

    public Excepciones(String llaveMapMensajeOErrorCodigo, String llaveMapMensajeOError, List<String> mensajes, HttpStatus codigo) {
        this.llaveMapMensajeOErrorCodigo = llaveMapMensajeOErrorCodigo;
        this.llaveMapMensajeOError = llaveMapMensajeOError;
        this.mensajes = mensajes;
        this.codigo = codigo;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }

    public String getLlaveMapMensajeOError() {
        return llaveMapMensajeOError;
    }

    public String getLlaveMapMensajeOErrorCodigo() {
        return llaveMapMensajeOErrorCodigo;
    }
}
