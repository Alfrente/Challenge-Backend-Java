package com.arroyo.cine.exception;

import org.springframework.http.HttpStatus;

public class Excepcion extends RuntimeException{
    private String llaveMapMensajeOCodigoError;
    private String llaveMapMensajeOError;
    private String mensaje;
    private HttpStatus codigo;

    public Excepcion() {
    }

    public Excepcion(String llaveMapMensajeOCodigoError, String llaveMapMensajeOError, String mensaje) {
        this.llaveMapMensajeOCodigoError = llaveMapMensajeOCodigoError;
        this.llaveMapMensajeOError = llaveMapMensajeOError;
        this.mensaje = mensaje;
    }

    public Excepcion(String llaveMapMensajeOCodigoError, String llaveMapMensajeOError, String mensaje, HttpStatus codigo) {
        this.llaveMapMensajeOCodigoError = llaveMapMensajeOCodigoError;
        this.llaveMapMensajeOError = llaveMapMensajeOError;
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }

    public String getLlaveMapMensajeOError() {
        return llaveMapMensajeOError;
    }

    public String getLlaveMapMensajeOCodigoError() {
        return llaveMapMensajeOCodigoError;
    }
}
