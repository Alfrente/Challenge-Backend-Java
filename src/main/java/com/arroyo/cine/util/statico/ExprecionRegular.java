package com.arroyo.cine.util.statico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExprecionRegular {
    private ExprecionRegular() {
    }

    private static final  String DIGITO = "\\d";
    private static final String UNO_AL_NUEVE = "[1-9]";
    public static final String UNO_AL_CINCO = "[1-5]";
    public static final String NUMERO = UNO_AL_NUEVE+"[0-9]*";
    public static final String EXPRECION_FECHA = UNO_AL_NUEVE + DIGITO + "{3}-" + DIGITO + "{2}-" + DIGITO + "{2}";
    public static final String EXPRECION_FECHA_SIN_CERO = UNO_AL_NUEVE + DIGITO + "{3}-" + UNO_AL_NUEVE + DIGITO +"?-" + UNO_AL_NUEVE + DIGITO +"?";
    public static final String EXPRECION_TEXTO_CON_ESPACIOS = "[A-Z][a-zA-Z\\ ]+";
    public static final String EXPRECION_TEXTO_CON_ESPACIOS_NUMERO = "[A-Z][a-zA-Z0-9\\ ]+";
    public static final String EXPRECION_TEXTO_CON_UN_ESPACIO = "[A-Z][a-z]{3,9}[ ][A-Z][a-z]{5,9}";
    public static final String EXPRECION_TEXTO_SIN_ESPACIOS = "[A-Z][a-z]{3,9}";

    public static final String EXPRECION_FORMATO_JPG = ".+.jpg";
    public static final String EXPRECION_FORMATO_PNG = ".+.png";
    public static final String EXPRECION_PESO = "[12]"+DIGITO+".?"+DIGITO+"?"+DIGITO+"?";
    public static final String EXPRECION_EDAD = UNO_AL_NUEVE+DIGITO+"?";
    public static final String EXPRECION_CORREO_GMAIL = "[a-zA-Z][a-zA-Z0-9-.]+@gmail.com";

    public static final String FORMATO_FECHA = "dd-MMMM-yyyy HH:mm:ss";
    public static final String FECHA_ACTUAL = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
}
