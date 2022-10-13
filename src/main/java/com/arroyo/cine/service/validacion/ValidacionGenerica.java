package com.arroyo.cine.service.validacion;

import com.arroyo.cine.exception.Excepcion;
import org.springframework.http.HttpStatus;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.MENSAJE_CODIGO_ERROR;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.ERROR;

public class ValidacionGenerica {

    private ValidacionGenerica() {
    }

    public static boolean validarNumero(String numero) {
        return !numero.matches(NUMERO);
    }

    public static int convertirEntero(String numero) {
        return Integer.parseInt(numero);
    }

    public static boolean validarDirectorioImagen(String imagen) {
        return imagen.matches(EXPRECION_FORMATO_JPG) || imagen.matches(EXPRECION_FORMATO_PNG);
    }

    public static boolean validarNombreConSinEspacio(String nombre) {
        return (nombre.matches(EXPRECION_TEXTO_SIN_ESPACIOS) || nombre.matches(EXPRECION_TEXTO_CON_UN_ESPACIO));
    }

    public static byte convertirByte(String numero) {
        return Byte.parseByte(numero);
    }

    public static float convertirFloat(String numero) {
        return Float.parseFloat(numero);
    }

    public static void validarId(String id, String mensaje){
        if (id == null || validarNumero(id))
            throw new Excepcion(MENSAJE_CODIGO_ERROR,ERROR,mensaje, HttpStatus.BAD_REQUEST );
    }
}
