package com.arroyo.cine.util;

public class ExpecionRegular {
    private static String dijito = "\\d";
    private static String indice = "[1-9]";
    public static String  FECHA_CORTA = indice + dijito + "{3}-" + dijito + "{2}-" + dijito + "{2}";
    public static String FECHA_LARGA = indice + dijito + "{3}-" + dijito + "{2}-" + dijito + "{2}T" + dijito + "{2}:" + dijito + "{2}:" + dijito + "{2}";
}
