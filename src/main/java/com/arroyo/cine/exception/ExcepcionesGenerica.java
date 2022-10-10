package com.arroyo.cine.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ExcepcionesGenerica extends RuntimeException{
    private String llaveMapMensajeErrorCodigo;
    private String llaveMapMensajeError;
    private List<String> mensajes;
    private HttpStatus codigo;

    public ExcepcionesGenerica() {
    }

    public ExcepcionesGenerica(String llaveMapMensajeErrorCodigo, String llaveMapMensajeError, List<String> mensajes, HttpStatus codigo) {
        this.llaveMapMensajeErrorCodigo = llaveMapMensajeErrorCodigo;
        this.llaveMapMensajeError = llaveMapMensajeError;
        this.mensajes = mensajes;
        this.codigo = codigo;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }

    public String getLlaveMapMensajeError() {
        return llaveMapMensajeError;
    }

    public String getLlaveMapMensajeErrorCodigo() {
        return llaveMapMensajeErrorCodigo;
    }
}
