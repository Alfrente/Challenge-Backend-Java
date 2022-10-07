package com.arroyo.cine.service;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepciones;
import com.arroyo.cine.util.statico.RespuestaExcepcion;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ValidacionCompartida {
    private static List<String> errores;

    private ValidacionCompartida() {
    }

    public static byte convertirByte(String numero) {
        return Byte.parseByte(numero);
    }

    public static float convertirFloat(String numero) {
        return Float.parseFloat(numero);
    }

    public static boolean validarEdad(String edad) {
        return (!edad.matches(EXPRECION_EDAD));
    }

    public static boolean validarPeso(String peso) {
        return (!peso.matches(EXPRECION_PESO));
    }

    private static boolean validarNombrePersonaje(String nombre) {
        if (nombre.matches(EXPRECION_NOMBRE_APELLIDO))
            return false;
        return !nombre.matches(EXPRECION_NOMBRE);
    }

    public static boolean validarDireccionImagen(String imagen) {
        if (imagen.matches(EXPRECION_DIRECCION_FORMATO_JPG))
            return false;
        return !imagen.matches(EXPRECION_DIRECCION_FORMATO_PNG);
    }

    public static void verificarParametrosEntradaPersonaje(PersonajeDto dto) {
        errores = new ArrayList<>();
        if (dto.getNombre() == null || validarNombrePersonaje(dto.getNombre()))
            errores.add(POR_FAVOR_INGRESE + "el nombre " + DE_EL + PERSONAJE + VALIDO);
        if (dto.getEdad() == null || validarEdad(dto.getEdad()))
            errores.add(POR_FAVOR_INGRESE + "la edad" + VALIDA);
        if (dto.getPeso() == null || validarPeso(dto.getPeso()))
            errores.add(POR_FAVOR_INGRESE + "el peso" + VALIDO);
        if (dto.getImagen() == null || validarDireccionImagen(dto.getImagen()))
            errores.add(INGRESE_DIRECCION_IMAGEN_INCORRECTA);
        if (dto.getHistoria() == null || dto.getHistoria().isBlank() || !dto.getHistoria().matches(EXPRECION_TEXTO_ESPACIO))
            errores.add(POR_FAVOR_INGRESE + "la historia" + VALIDA);
        if (!errores.isEmpty())
            throw new PersonajeExcepciones(CODIGO_ERROR,ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
