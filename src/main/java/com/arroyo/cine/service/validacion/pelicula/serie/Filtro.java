package com.arroyo.cine.service.validacion.pelicula.serie;

import com.arroyo.cine.exception.Excepciones;
import com.arroyo.cine.model.entity.PeliculaSerie;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarNumero;
import static com.arroyo.cine.util.statico.ExprecionRegular.TEXTO_CON_ESPACIOS;
import static com.arroyo.cine.util.statico.MensajeError.*;

public class Filtro {
    private Filtro() {
    }

    public static List<PeliculaSerie> filtroNombreGenero(List<PeliculaSerie> pelicula, String name, String genre) {
        return pelicula.stream().filter(pelicula1 -> pelicula1.getTitulo().equals(name) &&
                pelicula1.getIdGenero().equals(convertirEntero(genre))).map(setearNull).toList();
    }

    public static List<PeliculaSerie> filtroNombre(List<PeliculaSerie> pelicula, String name) {
        return pelicula.stream().filter(pelicula1 -> pelicula1.getTitulo().equals(name)).
                map(setearNull).toList();
    }

    public static List<PeliculaSerie> filtroGenero(List<PeliculaSerie> pelicula, String genre) {
        return pelicula.stream().filter(pelicula1 -> pelicula1.getIdGenero().equals(convertirEntero(genre))).
                map(setearNull).toList();
    }

    public static List<PeliculaSerie> filtroOrden(List<PeliculaSerie> pelicula, String order) {
        return ordenarLista(pelicula, order).stream().map(setearNull).toList();
    }

    private static final UnaryOperator<PeliculaSerie> setearNull = peliculaSerie -> {
        peliculaSerie.setIdPeliculaSerie(null);
        peliculaSerie.setPersonajes(null);
        peliculaSerie.setCalifiacion(null);
        peliculaSerie.setIdGenero(null);
        return peliculaSerie;
    };

    public static List<PeliculaSerie> ordenarLista(List<PeliculaSerie> peliculaSeries, String orden) {
        if (orden.equalsIgnoreCase("ASC"))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion));
        else if (orden.equalsIgnoreCase("DESC"))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion).reversed());
        return peliculaSeries;
    }

    public static int numeroFiltro(String name, String genre, String order) {
        if (name != null && !name.isBlank() && genre != null && convertirEntero(genre) > 0)
            return 1;
        else if (name != null && !name.isBlank())
            return 2;
        else if (genre != null && convertirEntero(genre) > 0)
            return 3;
        else if (order != null && !order.isBlank())
            return 4;
        return 0;
    }

    public static void validarFiltro(String name, String genre, String order) {
        List<String> errores = new ArrayList<>();
        if (name != null && !name.matches(TEXTO_CON_ESPACIOS))
            errores.add(POR_FAVOR_INGRESE + "el titulo de la " + PELICULA_SERIE + VALIDA);
        if (genre != null && validarNumero(genre))
            errores.add(POR_FAVOR_INGRESE + "el id genero"+ VALIDO);
        if (order != null && !order.matches("ASC") && order.matches("DESC"))
            errores.add(POR_FAVOR_INGRESE + "como ordenar la información" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
