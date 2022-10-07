package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.custom.ExcepcionGenerica;
import com.arroyo.cine.exception.custom.ExcepcionesGenerica;
import com.arroyo.cine.exception.custom.genero.GeneroExcepciones;
import com.arroyo.cine.exception.custom.genero.GeneroExcepcion;
import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepcion;
import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepciones;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepciones;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcion;
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
    public ResponseEntity<Map<String, String>> errorPeliculaSerieGenerico(PeliculaSerieExcepcion excepcion){
        return new ResponseEntity<>(generarMapaError(excepcion), excepcion.getCodigo());
    }

    @ExceptionHandler(PeliculaSerieExcepciones.class)
    public ResponseEntity<Map<String, String>> erroresPeliculaSerie(PeliculaSerieExcepciones excepciones) {
        return new ResponseEntity<>(generarMapaErrores(excepciones), excepciones.getCodigo());
    }

    private Map<String, String> generarMapaError(ExcepcionGenerica excepcion){
        this.response.put(MENSAJE, excepcion.getMensaje());
        this.response.put(CODIGO_ERROR, String.valueOf(excepcion.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return this.response;
    }

    private Map<String, String> generarMapaErrores(ExcepcionesGenerica excepciones){
        this.index = 0;
        this.mensajes.setLength(0);
        for (String mensaje : excepciones.getMensajes()) {
            this.index++;
            this.mensajes.append(index).append(" ").append(mensaje).append(" - ");
        }
        this.response.put(MENSAJE, this.mensajes.toString());
        this.response.put(CODIGO_ERROR, String.valueOf(excepciones.getCodigo().value()));
        this.response.put(FECHA, FECHA_ACTUAL);
        return response;
    }
}
