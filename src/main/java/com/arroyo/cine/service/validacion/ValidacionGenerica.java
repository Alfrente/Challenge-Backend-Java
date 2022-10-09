package com.arroyo.cine.service.validacion;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;

public class ValidacionGenerica {

    private ValidacionGenerica() {
    }

    public static boolean validarStringNumero(String numero) {
        return numero.matches(NUMERO);
    }

    public static int convertirEntero(String numero) {
        return Integer.parseInt(numero);
    }

    public static boolean validarDireccionImagen(String imagen) {
        return imagen.matches(EXPRECION_DIRECCION_FORMATO_JPG) || imagen.matches(EXPRECION_DIRECCION_FORMATO_PNG);
    }

    public static boolean validarIdNullMenorCero(Integer idGenero) {
         return idGenero == null || idGenero <= 0;
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
}
