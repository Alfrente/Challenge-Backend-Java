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
import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.service.validacion.pelicula.serie.Fecha.validarFecha;
import static com.arroyo.cine.service.validacion.pelicula.serie.Filtro.*;
import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.MensajeError.*;

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

    public static List<PeliculaSerie> filtroPeliculaSerie(List<PeliculaSerie> pelicula, String name, String genre, String order) {
        if (pelicula.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO, MENSAJE, NO_HAY + PELICULA_SERIE + DISPONIBLE, HttpStatus.OK);

        validarFiltro(name, genre, order);
        int numero = numeroFiltro(name, genre, order);

        switch (numero) {
            case 1:
                return filtroNombreGenero(pelicula, name, genre);
            case 2:
                return filtroNombre(pelicula, name);
            case 3:
                return filtroGenero(pelicula, genre);
            case 4:
                return filtroOrden(pelicula, order);
            default:
                return pelicula;
        }
    }

    public static void verificarParametrosEntradaPeliculaSerie(PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.getTitulo() == null || !dto.getTitulo().matches(TEXTO_CON_ESPACIOS))
            errores.add(POR_FAVOR_INGRESE + "el titulo de la " + PELICULA_SERIE + VALIDA);
        if (dto.getCaratula() == null || validarFormato(dto.getCaratula()))
            errores.add(IMAGEN_FORMATO_INCORRECTO);
        if (dto.getFechaCreacion() == null || validarFecha(dto.getFechaCreacion()))
            errores.add(POR_FAVOR_INGRESE + "una fecha" + VALIDA);
        if (dto.getCalifiacion() == null || !validarCalificacion(dto.getCalifiacion()))
            errores.add(POR_FAVOR_INGRESE + LA + CALIFICACION + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }


    public static PeliculaSerie parametroActualizar(PeliculaSerie entity, PeliculaSerieDto dto) {
        validarEntradaActualizar(dto);
        if (dto.getTitulo() != null && dto.getTitulo().matches(TEXTO_CON_ESPACIOS_NUMERO))
            entity.setTitulo(dto.getTitulo());
        if (dto.getCaratula() != null && validarFormato(dto.getCaratula()))
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
        if (dto.getTitulo() != null && !dto.getTitulo().matches(TEXTO_CON_ESPACIOS_NUMERO))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo de la " + PELICULA_SERIE + " " + dto.getTitulo());
        if (dto.getCaratula() != null && !validarFormato(dto.getCaratula()))
            errores.add(IMAGEN_FORMATO_INCORRECTO);
        if (dto.getFechaCreacion() != null && validarFecha(dto.getFechaCreacion()))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha " + dto.getFechaCreacion());
        if (dto.getCalifiacion() != null && !validarCalificacion(dto.getCalifiacion()))
            errores.add(POR_FAVOR_VERIFIQUE + LA + CALIFICACION + " " + dto.getCalifiacion());
        if (dto.getIdGenero() != null && convertirEntero(dto.getIdGenero()) <= 0)
            errores.add(POR_FAVOR_VERIFIQUE + EL + "id genero " + dto.getIdGenero());
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    private static boolean validarCalificacion(String calidicain) {
        return calidicain.matches("[1-5]");
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
            errores.add(POR_FAVOR_VERIFIQUE + "el id genero");
        if (peliculaSerie.getCalifiacion() != null && dto.getCalifiacion() != null && !peliculaSerie.getCalifiacion().equals(convertirByte(dto.getCalifiacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la calificación");
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
