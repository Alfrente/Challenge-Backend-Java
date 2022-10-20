package com.arroyo.cine.controller.advice;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdviceGlobalTest {
    private static final ControllerAdviceGlobal controllerAdviceGlobal = new ControllerAdviceGlobal();
    private static final Excepcion EXCEPCION = new Excepcion("Mensaje",
            "Codigo", "Error", HttpStatus.BAD_REQUEST);

    private static final Excepciones EXCEPCIONES = new Excepciones("Mensaje",
            "Codigo", List.of("Es una prueba","Es una prueba de error"), HttpStatus.BAD_REQUEST);
    @Test
    void error() {
        assertEquals(controllerAdviceGlobal.error(EXCEPCION), controllerAdviceGlobal.error(EXCEPCION));
    }

    @Test
    void errores() {
        assertEquals(controllerAdviceGlobal.errores(EXCEPCIONES), controllerAdviceGlobal.errores(EXCEPCIONES));
    }
}