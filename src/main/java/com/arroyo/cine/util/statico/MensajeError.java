package com.arroyo.cine.util.statico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.arroyo.cine.util.statico.ExprecionRegular.FORMATO_FECHA;

public class MensajeError {
    private MensajeError() {
    }
    public static final String ERROR = "Error";
    public static final String MENSAJE = "Mensaje";
    public static final String MENSAJE_CODIGO_ERROR = "Código error";
    public static final String MENSAJE_CODIGO = "Código ";
    public static final String EL = "El ";
    public static final String LA = "La ";
    public static final String POR_FAVOR_INGRESE = "Por favor ingrese ";
    public static final String POR_FAVOR_VERIFIQUE = "Por favor verifique ";
    public static final String DE_EL = "de el ";
    public static final String EL_ID = "el id ";
    public static final String NO_HAY = "No hay ";
    public static final String DISPONIBLE = " disponible";
    public static final String VALIDO = " valido.";
    public static final String VALIDA = " valida.";
    public static final String NO_DISPONIBLE = " no esta disponible.";
    public static final String IMAGEN_FORMATO_INCORRECTO = POR_FAVOR_INGRESE + "una imagen";
    public static final String INGRESE_DATOS_REQUERIDOS = "Por favor ingrese los datos obligatorios.";
    public static final String PERSONAJE = "personaje";
    public static final String GENERO = "genero";
    public static final String CALIFICACION = "calificación";
    public static final String PELICULA_SERIE = "película o serie";
    public static final String FECHA_ACTUAL = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
}
