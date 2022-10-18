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
        if (dto != null && dto.idPeliculaSerie() == null && dto.titulo() == null
                && dto.califiacion() == null && dto.imagen() == null
                && dto.fechaCreacion() == null && dto.idGenero() == null
        )
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static List<PeliculaSerie> filtroPeliculaSerie(List<PeliculaSerie> pelicula, String name, String genre, String order) {
        if (pelicula.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO, MENSAJE, NO_HAY + PELICULA_SERIE + DISPONIBLE, HttpStatus.OK);

        validarFiltro(name, genre, order);
        int numero = numeroFiltro(name, genre, order);

        return switch (numero) {
            case 1 -> filtroNombreGenero(pelicula, name, genre);
            case 2 -> filtroNombre(pelicula, name);
            case 3 -> filtroGenero(pelicula, genre);
            case 4 -> filtroOrden(pelicula, order);
            default -> pelicula;
        };
    }

    public static void verificarParametrosEntradaPeliculaSerie(PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.titulo() == null || !dto.titulo().matches(TEXTO_CON_ESPACIOS))
            errores.add(POR_FAVOR_INGRESE + "el titulo de la " + PELICULA_SERIE + VALIDA);
        if (dto.imagen() != null && !validarFormato(dto.imagen()))
            errores.add(IMAGEN_FORMATO_INCORRECTO + " valida para la película o serie");
        if (dto.fechaCreacion() == null || validarFecha(dto.fechaCreacion()))
            errores.add(POR_FAVOR_INGRESE + "una fecha" + VALIDA);
        if (dto.califiacion() == null || !validarCalificacion(dto.califiacion()))
            errores.add(POR_FAVOR_INGRESE + LA + CALIFICACION + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }


    public static PeliculaSerie parametroActualizar(PeliculaSerie entity, PeliculaSerieDto dto) {
        validarEntradaActualizar(dto);
        if (dto.titulo() != null && dto.titulo().matches(TEXTO_CON_ESPACIOS_NUMERO))
            entity.setTitulo(dto.titulo());
        if (dto.imagen() != null && validarFormato(dto.imagen()))
            entity.setImagen(dto.imagen());
        if (dto.fechaCreacion() != null && !validarFecha(dto.fechaCreacion()))
            entity.setFechaCreacion(LocalDate.parse(dto.fechaCreacion()));
        if (dto.califiacion() != null && validarCalificacion(dto.califiacion()))
            entity.setCalifiacion(convertirByte(dto.califiacion()));
        if (dto.idGenero() != null && convertirEntero(dto.idGenero()) > 0)
            entity.setIdGenero(convertirEntero(dto.idGenero()));
        return entity;
    }

    private static void validarEntradaActualizar(PeliculaSerieDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.titulo() != null && !dto.titulo().matches(TEXTO_CON_ESPACIOS_NUMERO))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo de la " + PELICULA_SERIE + " " + dto.titulo());
        if (dto.imagen() != null && !validarFormato(dto.imagen()))
            errores.add(IMAGEN_FORMATO_INCORRECTO);
        if (dto.fechaCreacion() != null && validarFecha(dto.fechaCreacion()))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha " + dto.fechaCreacion());
        if (dto.califiacion() != null && !validarCalificacion(dto.califiacion()))
            errores.add(POR_FAVOR_VERIFIQUE + LA + CALIFICACION + " " + dto.califiacion());
        if (dto.idGenero() != null && convertirEntero(dto.idGenero()) <= 0)
            errores.add(POR_FAVOR_VERIFIQUE + EL + "id genero " + dto.idGenero());
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
        if (peliculaSerie.getTitulo() != null && dto.titulo() != null && !peliculaSerie.getTitulo().equals(dto.titulo()))
            errores.add(POR_FAVOR_VERIFIQUE + "el titulo");
        if (peliculaSerie.getFechaCreacion() != null && dto.fechaCreacion() != null && !peliculaSerie.getFechaCreacion().equals(LocalDate.parse(dto.fechaCreacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la fecha de creación");
        if (peliculaSerie.getIdGenero() != null && dto.idGenero() != null && !peliculaSerie.getIdGenero().equals(convertirEntero(dto.idGenero())))
            errores.add(POR_FAVOR_VERIFIQUE + "el id genero");
        if (peliculaSerie.getCalifiacion() != null && dto.califiacion() != null && !peliculaSerie.getCalifiacion().equals(convertirByte(dto.califiacion())))
            errores.add(POR_FAVOR_VERIFIQUE + "la calificación");
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
