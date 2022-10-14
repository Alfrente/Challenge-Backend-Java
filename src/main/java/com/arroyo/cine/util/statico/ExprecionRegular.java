package com.arroyo.cine.util.statico;

public class ExprecionRegular {
    private ExprecionRegular() {
    }

    private static final  String DIGITO = "\\d";
    private static final String UNO_AL_NUEVE = "[1-9]";
    public static final String NUMERO = UNO_AL_NUEVE+"[0-9]*";
    public static final String FECHA = UNO_AL_NUEVE + DIGITO + "{3}-" + DIGITO + "{2}-" + DIGITO + "{2}";
    public static final String TEXTO_CON_ESPACIOS = "[A-Z][a-zA-Z\\ ]+";
    public static final String TEXTO_CON_ESPACIOS_NUMERO = "[A-Z][a-zA-Z0-9\\ ]+";
    public static final String TEXTO_ESPACIO_NUMERO = "[A-Z][a-zA-Z1-9\\ ]{3,47}";
    public static final String PESO = "[12]"+DIGITO+".?"+DIGITO+"?"+DIGITO+"?";
    public static final String EDAD = UNO_AL_NUEVE+DIGITO+"?";
    public static final String CORREO_GMAIL = "[a-zA-Z][a-zA-Z0-9-.]+@gmail.com";
    public static final String FORMATO_FECHA = "dd-MMMM-yyyy HH:mm:ss";
}
