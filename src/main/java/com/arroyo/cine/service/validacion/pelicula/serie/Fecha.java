package com.arroyo.cine.service.validacion.pelicula.serie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.arroyo.cine.util.statico.ExprecionRegular.FECHA;

public class Fecha {

    private Fecha() {
    }

    public static boolean validarFecha(String fechaEntrda) {
        if (fechaEntrda.matches(FECHA))
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
}
