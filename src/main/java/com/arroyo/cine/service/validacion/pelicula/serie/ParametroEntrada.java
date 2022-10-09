package com.arroyo.cine.service.validacion.pelicula.serie;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.PeliculaSerie;
import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepcion;
import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepciones;
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

    private static List<String> errores;

    public static boolean validarFechaExprecion(String fecha) {
        return fecha.matches(EXPRECION_FECHA);
    }

    public static PeliculaSerieDto setearPersonajesNull(PeliculaSerieDto peliculaSerieDto) {
        peliculaSerieDto.setPersonajes(null);
        return peliculaSerieDto;
    }

    public static List<PeliculaSerie> ordenarLista(List<PeliculaSerie> peliculaSeries, String orden) {
        if (orden.equalsIgnoreCase(ASC))
            peliculaSeries.sort(Comparator.comparing(PeliculaSerie::getFechaCreacion));
        else if (orden.equalsIgnoreCase(DESC))
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

    public static boolean validarDatosPersonajes(List<PersonajeDto> personajesRecibidos) {
        if (personajesRecibidos == null)
            return false;
        int index = personajesRecibidos.size();
        int personajesValido = 0;

        for (var personaje : personajesRecibidos) {
            if (personaje.getEdad() != null && convertirByte(personaje.getEdad()) > 0 && personaje.getNombre() != null &&
                    (!personaje.getNombre().isBlank()) && personaje.getPeso() != null && convertirFloat(personaje.getPeso()) > 0)
                personajesValido++;
        }
        return personajesValido == index;
    }

    public static List<PeliculaSerie> filtroPeliculaSerie(List<PeliculaSerie> peliculaSeries, String name, Integer genre, String order) {
        if (peliculaSeries.isEmpty())
            throw new PeliculaSerieExcepcion(CODIGO, MENSAJE, NO_HAY + PELICULA_SERIE + DISPONIBLE, HttpStatus.OK);
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
        errores = new ArrayList<>();
        if (dto.getTitulo() == null || !validarNombreConSinEspacio(dto.getTitulo()))
            errores.add(POR_FAVOR_INGRESE + "un titulo" + VALIDO);
        if (dto.getCaratula() == null || validarDireccionImagen(dto.getCaratula()))
            errores.add(INGRESE_DIRECCION_IMAGEN_INCORRECTA);
        if (dto.getFechaCreacion() == null || validarFecha(dto.getFechaCreacion()))
            errores.add(POR_FAVOR_INGRESE + "una fecha" + VALIDA);
        if (dto.getCalifiacion() == null || !validarCalificacion(convertirByte(dto.getCalifiacion())))
            errores.add(POR_FAVOR_INGRESE + LA + "calificación" + VALIDA);
        throw new PeliculaSerieExcepciones(CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    private static boolean validarCalificacion(Byte calificacion){
        return (calificacion > 0 && calificacion <= 5);
    }

    public static PeliculaSerie identificarParametroActualizar(PeliculaSerie entity, PeliculaSerieDto dto) {
        if (dto.getTitulo() != null && validarNombreConSinEspacio(dto.getTitulo()))
            entity.setTitulo(dto.getTitulo());
        if (dto.getCaratula() != null && !validarDireccionImagen(dto.getCaratula()))
            entity.setImagen(dto.getCaratula());
        if (dto.getFechaCreacion() != null && !validarFecha(dto.getFechaCreacion()))
            entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        if (dto.getCalifiacion() != null && validarCalificacion(convertirByte(dto.getCalifiacion())))
            entity.setCalifiacion(convertirByte(dto.getCalifiacion()));
        if (dto.getIdGenero() != null && convertirEntero(dto.getIdGenero()) > 0)
            entity.setIdGenero(convertirEntero(dto.getIdGenero()));
        return entity;
    }

    public static void verificarParametrosEntradaPersonajes(List<PersonajeDto> personajeDtos) {
        if (personajeDtos != null)
            personajeDtos.forEach(ParametroEntradaPersonaje::verificarParametrosEntradaPersonaje);
    }

    public static void verificarPeliculaSerieDto(PeliculaSerieDto peliculaSerie) {
        if (peliculaSerie == null || peliculaSerie.getIdPeliculaSerie() == null)
            throw new PeliculaSerieExcepcion(CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarDatosSonIgual(PeliculaSerie peliculaSerie, PeliculaSerieDto dto) {
        errores = new ArrayList<>();
        if (peliculaSerie.getTitulo() != null && dto.getTitulo() != null && !peliculaSerie.getTitulo().equals(dto.getTitulo()))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo");
        if (peliculaSerie.getFechaCreacion() != null && dto.getFechaCreacion() != null && !peliculaSerie.getFechaCreacion().equals(LocalDate.parse(dto.getFechaCreacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha de creación");
        if (peliculaSerie.getIdGenero() != null && dto.getIdGenero() != null && !peliculaSerie.getIdGenero().equals(convertirEntero(dto.getIdGenero())))
            errores.add(POR_FAVOR_VERIFIQUE + "el id " + DE_EL + "genero");
        if (peliculaSerie.getCalifiacion() != null && dto.getCalifiacion() != null && !peliculaSerie.getCalifiacion().equals(convertirByte(dto.getCalifiacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la calificación");
        if (!errores.isEmpty())
            throw new PeliculaSerieExcepciones(CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

}
