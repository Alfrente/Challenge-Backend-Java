package com.arroyo.cine.service.validacion;

import com.arroyo.cine.exception.Excepcion;
import org.springframework.http.HttpStatus;

import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.MensajeError.ERROR;
import static com.arroyo.cine.util.statico.MensajeError.MENSAJE_CODIGO_ERROR;

public class ValidacionGenerica {

    private ValidacionGenerica() {
    }

    public static boolean validarNumero(String numero) {
        return !numero.matches(NUMERO);
    }

    public static int convertirEntero(String numero) {
        return Integer.parseInt(numero);
    }

    public static boolean validarFormato(String imagen) {
        if (imagen == null)
            return false;
        return imagen.endsWith(".png") || imagen.endsWith(".PNG") || imagen.endsWith(".jpg") || imagen.endsWith(".JPG");
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
