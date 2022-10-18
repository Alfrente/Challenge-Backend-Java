package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.arroyo.cine.util.statico.MensajeError.FECHA_ACTUAL;

@ControllerAdvice
public class ControllerAdviceGlobal {

    @ExceptionHandler(Excepcion.class)
    public ResponseEntity<Map<String, String>> error(Excepcion excepcion) {
        return new ResponseEntity<>(generarMapaError(excepcion), excepcion.getCodigo());
    }

    @ExceptionHandler(Excepciones.class)
    public ResponseEntity<Map<String, String>> errores(Excepciones excepciones) {
        return new ResponseEntity<>(generarMapaErrores(excepciones), excepciones.getCodigo());
    }

    private Map<String, String> generarMapaError(Excepcion excepcion) {
        Map<String, String> response = new HashMap<>();
        response.put(excepcion.getLlaveMapMensajeOError(), excepcion.getMensaje());
        response.put(excepcion.getLlaveMapMensajeOCodigoError(), String.valueOf(excepcion.getCodigo().value()));
        response.put("Fecha", FECHA_ACTUAL);
        return response;
    }

    private Map<String, String> generarMapaErrores(Excepciones excepciones) {
        Map<String, String> response = new HashMap<>();
        StringBuilder mensajes = new StringBuilder();
        int index = 0;
        mensajes.setLength(0);
        for (String mensaje : excepciones.getMensajes()) {
            index++;
            mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        mensajes.replace(mensajes.length() - 2, mensajes.length(), "");
        response.put(excepciones.getLlaveMapMensajeOError(), mensajes.toString());
        response.put(excepciones.getLlaveMapMensajeOErrorCodigo(), String.valueOf(excepciones.getCodigo().value()));
        response.put("Fecha", FECHA_ACTUAL);
        return response;
    }
}
