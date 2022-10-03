package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.custom.genero.GeneroDatosExcepciones;
import com.arroyo.cine.exception.custom.genero.GeneroExcepcionGenerico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.arroyo.cine.util.InformacionEstatica.FORMATO_FECHA;

@ControllerAdvice
public class ControllerAdviceGlobal {
    private Map<String, String> response;

    public ControllerAdviceGlobal() {
        response = new HashMap<>();
    }

    @ExceptionHandler(GeneroExcepcionGenerico.class)
    public ResponseEntity<Map<String, String>> errorGenerico(GeneroExcepcionGenerico excepcion) {
        this.response.put("Mensaje", excepcion.getMensaje());
        this.response.put("Código error", String.valueOf(excepcion.getCodigo().value()));
        this.response.put("Fecha", excepcion.getFecha().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
        return new ResponseEntity<>(this.response, excepcion.getCodigo());
    }

    @ExceptionHandler(GeneroDatosExcepciones.class)
    public ResponseEntity<Map<String, String>> errorDatosGenero(GeneroDatosExcepciones excepciones) {
        int index = 0;
        StringBuilder mensajes = new StringBuilder();
        for (String mensaje : excepciones.getMensajes()) {
            index++;
            mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        this.response.put("Mensaje", mensajes.toString());
        this.response.put("Código error", String.valueOf(excepciones.getCodigo().value()));
        this.response.put("Fecha", excepciones.getFecha().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
        return new ResponseEntity<>(this.response, excepciones.getCodigo());
    }

}
