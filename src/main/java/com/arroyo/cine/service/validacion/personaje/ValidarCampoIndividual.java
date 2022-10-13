package com.arroyo.cine.service.validacion.personaje;

import com.arroyo.cine.exception.Excepcion;
import org.springframework.http.HttpStatus;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.ExprecionRegular.EXPRECION_TEXTO_SIN_ESPACIOS;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ValidarCampoIndividual {

    private ValidarCampoIndividual() {
    }

    public static boolean validarEdad(String edad) {
        return !edad.matches(EXPRECION_EDAD);
    }

    public static boolean validarPeso(String peso) {
        return (!peso.matches(EXPRECION_PESO));
    }

    public static boolean validarHistoria(String historia) {
        return !historia.matches(EXPRECION_TEXTO_CON_ESPACIOS);
    }

    public static boolean validarNombre(String nombre) {
        if (nombre.matches(EXPRECION_TEXTO_CON_UN_ESPACIO))
            return false;
        return (!nombre.matches(EXPRECION_TEXTO_SIN_ESPACIOS));
    }

    public static void validarEdadError(String edad) {
        if (edad != null && validarEdad(edad))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "la edad", HttpStatus.BAD_REQUEST);
    }

    public static void validarPesoError(String peso) {
        if (peso != null && validarPeso(peso))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "el peso", HttpStatus.BAD_REQUEST);
    }

    public static void validarHistoriaError(String historia) {
        if (historia != null && validarHistoria(historia))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "la historia", HttpStatus.BAD_REQUEST);
    }

    public static void validarNombreError(String nombre) {
        if (nombre != null && validarNombre(nombre))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "el nombre", HttpStatus.BAD_REQUEST);
    }
}
