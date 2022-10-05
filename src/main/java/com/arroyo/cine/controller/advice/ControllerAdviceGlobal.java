package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.custom.genero.GeneroExcepciones;
import com.arroyo.cine.exception.custom.genero.GeneroExcepcionGenerico;
import com.arroyo.cine.exception.custom.pelicula.serie.PersonajeExcepciones;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcionGenerico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.arroyo.cine.util.statico.ExprecionRegular.FECHA_ACTUAL;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

@ControllerAdvice
public class ControllerAdviceGlobal {
    private final Map<String, String> response;
    private StringBuilder mensajes;

    private int index = 0;

    public ControllerAdviceGlobal() {
        response = new HashMap<>();
        mensajes = new StringBuilder();
    }

    @ExceptionHandler(GeneroExcepcionGenerico.class)
    public ResponseEntity<Map<String, String>> errorGeneroGenerico(GeneroExcepcionGenerico excepcion) {
        this.response.put(MENSAJE, excepcion.getMensaje());
        this.response.put(CODIGO_ERROR, String.valueOf(excepcion.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return new ResponseEntity<>(this.response, excepcion.getCodigo());
    }

    @ExceptionHandler(GeneroExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresGenero(GeneroExcepciones excepciones) {
        this.index = 0;
        this.mensajes.setLength(0);
        for (String mensaje : excepciones.getMensajes()) {
            this.index++;
            this.mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        this.response.put(MENSAJE, this.mensajes.toString());
        this.response.put(CODIGO_ERROR, String.valueOf(excepciones.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return new ResponseEntity<>(this.response, excepciones.getCodigo());
    }

    @ExceptionHandler(PersonajeExcepcionGenerico.class)
    public ResponseEntity<Map<String, String>> errorPersonajeGenerico(PersonajeExcepcionGenerico excepcion) {
        this.response.put(MENSAJE, excepcion.getMensaje());
        this.response.put(CODIGO_ERROR, String.valueOf(excepcion.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return new ResponseEntity<>(this.response, excepcion.getCodigo());
    }

    @ExceptionHandler(PersonajeExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresPersonaje(PersonajeExcepciones excepciones) {
        this.index = 0;
        this.mensajes.setLength(0);
        for (String mensaje : excepciones.getMensajes()) {
            this.index++;
            this.mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        this.response.put(MENSAJE, this.mensajes.toString());
        this.response.put(CODIGO_ERROR, String.valueOf(excepciones.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return new ResponseEntity<>(this.response, excepciones.getCodigo());
    }
}
