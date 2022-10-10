package com.arroyo.cine.service.validacion;

import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepcion;
import org.springframework.http.HttpStatus;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ValidacionGenerica {

    private ValidacionGenerica() {
    }

    public static boolean validarStringNumero(String numero) {
        return !numero.matches(NUMERO);
    }

    public static int convertirEntero(String numero) {
        return Integer.parseInt(numero);
    }

    public static boolean validarDireccionImagen(String imagen) {
        return imagen.matches(EXPRECION_DIRECCION_FORMATO_JPG) || imagen.matches(EXPRECION_DIRECCION_FORMATO_PNG);
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
        if (id == null || validarStringNumero(id))
            throw new PeliculaSerieExcepcion(CODIGO_ERROR,ERROR,mensaje, HttpStatus.BAD_REQUEST );
    }
}
