package com.arroyo.cine.util;

public class InformacionEstatica {
    private static final  String DIGITO = "\\d";
    public static  final String REGEX_FECHA = "[1-9]" + DIGITO + "{3}-" + DIGITO + "{2}-" + DIGITO + "{2}";
    public static final  String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String REGEX_NOMBRE_GENERO = "[A-Z][a-zA-Z\\ ]{4,49}";
    public static final String FORMATO_FECHA = "dd-MMMM-yyyy HH:mm:ss";
}
