package com.arroyo.cine.exception.custom;

import org.springframework.http.HttpStatus;

public class ExcepcionGenerica extends RuntimeException{
    private String llaveMapMensajeErrorCodigo;
    private String llaveMapMensajeError;
    private String mensaje;
    private HttpStatus codigo;

    public ExcepcionGenerica() {
    }

    public ExcepcionGenerica(String llaveMapMensajeErrorCodigo, String llaveMapMensajeError, String mensaje, HttpStatus codigo) {
        this.llaveMapMensajeErrorCodigo = llaveMapMensajeErrorCodigo;
        this.llaveMapMensajeError = llaveMapMensajeError;
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
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
