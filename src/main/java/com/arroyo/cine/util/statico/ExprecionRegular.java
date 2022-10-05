package com.arroyo.cine.util.statico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExprecionRegular {
    private ExprecionRegular() {
    }

    private static final  String DIGITO = "\\d";
    private static  final String UNO_AL_NUEVE = "[1-9]";
    public static  final String EXPRECION_FECHA = UNO_AL_NUEVE + DIGITO + "{3}-" + DIGITO + "{2}-" + DIGITO + "{2}";
    public static final  String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String EXPRECION_TEXTO_ESPACIO = "[a-zA-Z\\ ]+";
    public static final String EXPRECION_NOMBRE_APELLIDO = "[A-Z][a-z]{3,9}[ ][A-Z][a-z]{6,9}";
    public static final String EXPRECION_NOMBRE = "[A-Z][a-z]{3,9}";
    public static final String FORMATO_FECHA = "dd-MMMM-yyyy HH:mm:ss";
    public static final String FECHA_ACTUAL = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
    public static final String EXPRECION_DIRECCION_FORMATO_JPG = "[ABCDFG]:/Users/[a-zA-Z0-9-/]+.[j][p][g]";
    public static final String EXPRECION_DIRECCION_FORMATO_PNG = "[ABCDFG]:/Users/[a-zA-Z0-9-/]+.[p][n][g]";
    public static final String EXPRECION_PESO = "[12]"+DIGITO+"."+DIGITO+DIGITO+"?";
    public static final String EXPRECION_EDAD = UNO_AL_NUEVE+DIGITO+"?";
}
