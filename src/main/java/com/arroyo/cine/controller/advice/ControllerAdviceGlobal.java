package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.ExcepcionGenerica;
import com.arroyo.cine.exception.ExcepcionesGenerica;
import com.arroyo.cine.exception.genero.GeneroExcepcion;
import com.arroyo.cine.exception.genero.GeneroExcepciones;
import com.arroyo.cine.exception.pelicula.serie.PeliculaSerieExcepcion;
import com.arroyo.cine.exception.pelicula.serie.PeliculaSerieExcepciones;
import com.arroyo.cine.exception.personaje.PersonajeExcepcion;
import com.arroyo.cine.exception.personaje.PersonajeExcepciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.arroyo.cine.util.statico.ExprecionRegular.FECHA_ACTUAL;

@ControllerAdvice
public class ControllerAdviceGlobal {
    private static Map<String, String> response;
    private StringBuilder mensajes;

    private int index = 0;

    public ControllerAdviceGlobal() {
        response = new HashMap<>();
        mensajes = new StringBuilder();
    }

    @ExceptionHandler(GeneroExcepcion.class)
    public ResponseEntity<Map<String, String>> errorGeneroGenerico(GeneroExcepcion excepcion) {
        return new ResponseEntity<>(generarMapaError(excepcion), excepcion.getCodigo());
    }

    @ExceptionHandler(GeneroExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresGenero(GeneroExcepciones excepciones) {
        return new ResponseEntity<>(generarMapaErrores(excepciones), excepciones.getCodigo());
    }

    @ExceptionHandler(PersonajeExcepcion.class)
    public ResponseEntity<Map<String, String>> errorPersonajeGenerico(PersonajeExcepcion excepcion) {
        return new ResponseEntity<>(generarMapaError(excepcion), excepcion.getCodigo());
    }

    @ExceptionHandler(PersonajeExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresPersonaje(PersonajeExcepciones excepciones) {
        return new ResponseEntity<>(generarMapaErrores(excepciones), excepciones.getCodigo());
    }

    @ExceptionHandler(PeliculaSerieExcepcion.class)
    public ResponseEntity<Map<String, String>> errorPeliculaSerieGenerico(PeliculaSerieExcepcion excepcion) {
        return new ResponseEntity<>(generarMapaError(excepcion), excepcion.getCodigo());
    }

    @ExceptionHandler(PeliculaSerieExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresPeliculaSerie(PeliculaSerieExcepciones excepciones) {
        return new ResponseEntity<>(generarMapaErrores(excepciones), excepciones.getCodigo());
    }

    private Map<String, String> generarMapaError(ExcepcionGenerica excepcion) {
        response.clear();
        response.put(excepcion.getLlaveMapMensajeError(), excepcion.getMensaje());
        response.put(excepcion.getLlaveMapMensajeErrorCodigo(), String.valueOf(excepcion.getCodigo().value()));
        response.put("Fecha", FECHA_ACTUAL);
        return response;
    }

    private Map<String, String> generarMapaErrores(ExcepcionesGenerica excepciones) {
        this.index = 0;
        response.clear();
        mensajes.setLength(0);
        for (String mensaje : excepciones.getMensajes()) {
            this.index++;
            mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        mensajes.replace(mensajes.length()-2, mensajes.length(), "");
        response.put(excepciones.getLlaveMapMensajeError(), mensajes.toString());
        response.put(excepciones.getLlaveMapMensajeErrorCodigo(), String.valueOf(excepciones.getCodigo().value()));
        response.put("Fecha", FECHA_ACTUAL);
        return response;
    }
}
