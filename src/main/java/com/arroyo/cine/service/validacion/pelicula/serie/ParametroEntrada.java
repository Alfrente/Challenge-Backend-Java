package com.arroyo.cine.service.validacion.pelicula.serie;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import com.arroyo.cine.model.dto.PeliculaSerieDto;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.PeliculaSerie;
import com.arroyo.cine.service.validacion.personaje.ParametroEntradaPersonaje;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.service.validacion.pelicula.serie.Fecha.validarFecha;
import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ParametroEntrada {

    private ParametroEntrada() {
    }

    public static void validarPeliculaSerieDto(PeliculaSerieDto dto) {
        if (dto != null && dto.getIdPeliculaSerie() == null && dto.getTitulo() == null
                && dto.getCalifiacion() == null && dto.getCaratula() == null
                && dto.getFechaCreacion() == null && dto.getIdGenero() == null
        )
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static List<PeliculaSerie> ordenarLista(List<PeliculaSerie> peliculaSeries, String orden) {
        if (orden.equalsIgnoreCase("ASC"))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion));
        else if (orden.equalsIgnoreCase("DESC"))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion).reversed());
        return peliculaSeries;
    }

    private static final UnaryOperator<PeliculaSerie> setearNull = peliculaSerie -> {
        peliculaSerie.setIdPeliculaSerie(null);
        peliculaSerie.setPersonajes(null);
        peliculaSerie.setCalifiacion(null);
        peliculaSerie.setIdGenero(null);
        return peliculaSerie;
    };

    public static List<PeliculaSerie> filtroPeliculaSerie(List<PeliculaSerie> peliculaSeries, String name, Integer genre, String order) {
        if (peliculaSeries.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO, MENSAJE, NO_HAY + PELICULA_SERIE + DISPONIBLE, HttpStatus.OK);
        if (name != null && !name.isBlank() && genre != null && genre > 0) {
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equals(name) && peliculaSerie.getIdGenero().equals(genre)).map(setearNull).collect(Collectors.toList());
        } else if (name != null && !name.isBlank())
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equals(name)).map(setearNull).collect(Collectors.toList());
        else if (genre != null && genre > 0)
            return peliculaSeries.stream().filter(peliculaSerie -> peliculaSerie.getIdGenero().equals(genre)).map(setearNull).collect(Collectors.toList());
        else if (order != null && !order.isBlank())
            return ordenarLista(peliculaSeries, order).stream().map(setearNull).collect(Collectors.toList());
        else if (name == null && genre == null && order == null)
            return peliculaSeries;
        else
            return new ArrayList<>();
    }

    public static void verificarParametrosEntradaPeliculaSerie(PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.getTitulo() == null || !dto.getTitulo().matches(EXPRECION_TEXTO_CON_ESPACIOS))
            errores.add(POR_FAVOR_INGRESE + "el titulo de la " + PELICULA_SERIE + VALIDA);
        if (dto.getCaratula() == null || validarDirectorioImagen(dto.getCaratula()))
            errores.add(INGRESE_DIRECCION_IMAGEN_INCORRECTA);
        if (dto.getFechaCreacion() == null || validarFecha(dto.getFechaCreacion()))
            errores.add(POR_FAVOR_INGRESE + "una fecha" + VALIDA);
        if (dto.getCalifiacion() == null || !validarCalificacion(dto.getCalifiacion()))
            errores.add(POR_FAVOR_INGRESE + LA + "calificación" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }


    public static PeliculaSerie identificarParametroActualizar(PeliculaSerie entity, PeliculaSerieDto dto) {
        validarEntradaActualizar(dto);
        if (dto.getTitulo() != null && dto.getTitulo().matches(EXPRECION_TEXTO_CON_ESPACIOS_NUMERO))
            entity.setTitulo(dto.getTitulo());
        if (dto.getCaratula() != null && validarDirectorioImagen(dto.getCaratula()))
            entity.setImagen(dto.getCaratula());
        if (dto.getFechaCreacion() != null && !validarFecha(dto.getFechaCreacion()))
            entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        if (dto.getCalifiacion() != null && validarCalificacion(dto.getCalifiacion()))
            entity.setCalifiacion(convertirByte(dto.getCalifiacion()));
        if (dto.getIdGenero() != null && convertirEntero(dto.getIdGenero()) > 0)
            entity.setIdGenero(convertirEntero(dto.getIdGenero()));
        return entity;
    }

    private static void validarEntradaActualizar(PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.getTitulo() != null && !dto.getTitulo().matches(EXPRECION_TEXTO_CON_ESPACIOS_NUMERO))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo de la " + PELICULA_SERIE + VALIDA);
        if (dto.getCaratula() != null && !validarDirectorioImagen(dto.getCaratula()))
            errores.add(INGRESE_DIRECCION_IMAGEN_INCORRECTA);
        if (dto.getFechaCreacion() != null && validarFecha(dto.getFechaCreacion()))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha");
        if (dto.getCalifiacion() != null && !validarCalificacion(dto.getCalifiacion()))
            errores.add(POR_FAVOR_VERIFIQUE + LA + "calificación");
        if (dto.getIdGenero() != null && convertirEntero(dto.getIdGenero()) <= 0)
            errores.add(POR_FAVOR_VERIFIQUE + LA + "calificación" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    private static boolean validarCalificacion(String calidicain){
        return calidicain.matches(UNO_AL_CINCO);
    }

    public static void verificarParametrosEntradaPersonajes(List<PersonajeDto> personajeDtos) {
        if (personajeDtos != null)
            personajeDtos.forEach(ParametroEntradaPersonaje::validarDatoRecibido);
    }

    public static void validarDatosSonIgual(PeliculaSerie peliculaSerie, PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (peliculaSerie.getTitulo() != null && dto.getTitulo() != null && !peliculaSerie.getTitulo().equals(dto.getTitulo()))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo");
        if (peliculaSerie.getFechaCreacion() != null && dto.getFechaCreacion() != null && !peliculaSerie.getFechaCreacion().equals(LocalDate.parse(dto.getFechaCreacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha de creación");
        if (peliculaSerie.getIdGenero() != null && dto.getIdGenero() != null && !peliculaSerie.getIdGenero().equals(convertirEntero(dto.getIdGenero())))
            errores.add(POR_FAVOR_VERIFIQUE + "el id " + DE_EL + "genero");
        if (peliculaSerie.getCalifiacion() != null && dto.getCalifiacion() != null && !peliculaSerie.getCalifiacion().equals(convertirByte(dto.getCalifiacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la calificación");
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
