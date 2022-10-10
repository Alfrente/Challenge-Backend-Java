package com.arroyo.cine.service.validacion.pelicula.serie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.arroyo.cine.util.statico.ExprecionRegular.EXPRECION_FECHA;
import static com.arroyo.cine.util.statico.ExprecionRegular.EXPRECION_FECHA_SIN_CERO;

public class Fecha {

    private Fecha() {
    }

    public static boolean validarFecha(String fechaEntrda) {
        if (fechaEntrda.matches(EXPRECION_FECHA))
            return verificarFecha(fechaEntrda);
        return true;
    }

    private static boolean verificarFecha(String fechaValidaExprecion) {
        try {
            LocalDate.parse(fechaValidaExprecion).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    private static String generarFecha(String fechaValidadExprecion) {
        StringBuilder fechaArreglada = new StringBuilder();
        fechaArreglada.setLength(0);
        String[] arrayFecha = fechaValidadExprecion.split("-");
        for (String numero : arrayFecha) {
            if (numero.length() == 1) fechaArreglada.append("0").append(numero).append("-");
            else fechaArreglada.append(numero).append("-");
        }
        fechaArreglada.replace(10, 11, "");
        return String.valueOf(fechaArreglada);
    }
}
