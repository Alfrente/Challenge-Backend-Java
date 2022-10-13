package com.arroyo.cine.service.validacion.personaje;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.service.validacion.imagen.GuardarImagen.guardarImagen;
import static com.arroyo.cine.service.validacion.personaje.Filtro.*;
import static com.arroyo.cine.service.validacion.personaje.ValidarCampoIndividual.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ParametroEntradaPersonaje {

    private ParametroEntradaPersonaje() {
    }

    public static void validarPersonajeDto(String nombre, String edad, String peso) {
        if (nombre == null && edad == null && peso == null)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarPersonajeDto(PersonajeDto dto) {
        if (dto != null && dto.getIdPersonaje() == null && dto.getHistoria() == null
                && dto.getPeso() == null && dto.getEdad() == null && dto.getNombre() == null
        )
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }


    public static void compararPersonajeConPersonajeDto(Personaje db, PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (!db.getIdPersonaje().equals(convertirEntero(dto.getIdPersonaje())))
            errores.add(POR_FAVOR_VERIFIQUE + "el id " + DE_EL + PERSONAJE + ".");
        if (!db.getNombre().equals(dto.getNombre()))
            errores.add(POR_FAVOR_VERIFIQUE + "el nombre " + DE_EL + PERSONAJE + ".");
        if (!db.getEdad().equals(convertirByte(dto.getEdad())))
            errores.add(POR_FAVOR_VERIFIQUE + "la edad " + DE_EL + PERSONAJE + ".");
        if (!db.getPeso().equals(convertirFloat(dto.getPeso())))
            errores.add(POR_FAVOR_VERIFIQUE + "el peso " + DE_EL + PERSONAJE + ".");
        if (!db.getImagen().equals(dto.getImagen()))
            errores.add(POR_FAVOR_VERIFIQUE + "la dirección de la imagen " + DE_EL + PERSONAJE + ".");
        if (!db.getHistoria().equals(dto.getHistoria()))
            errores.add(POR_FAVOR_VERIFIQUE + "la historia " + DE_EL + PERSONAJE + ".");
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    public static void validarDatoRecibido(PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.getNombre() == null || validarNombre(dto.getNombre()))
            errores.add(POR_FAVOR_INGRESE + "el nombre " + DE_EL + PERSONAJE);
        if (dto.getEdad() == null || validarEdad(dto.getEdad()))
            errores.add(POR_FAVOR_INGRESE + "la edad" + VALIDA);
        if (dto.getPeso() == null || validarPeso(dto.getPeso()))
            errores.add(POR_FAVOR_INGRESE + "el peso" + VALIDO);
        if (dto.getHistoria() != null && validarHistoria(dto.getHistoria()))
            errores.add(POR_FAVOR_INGRESE + "la historia" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    public static List<Personaje> filtroPersonaje(List<Personaje> personajes, String name, String age, String movie, Boolean ponerCampoNull) {
        if (personajes.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO, MENSAJE, NO_HAY + PERSONAJE + DISPONIBLE, HttpStatus.OK);

        validarDatoEntradaFiltro(name, age, movie);
        int numero = numeroFiltro(name, age, movie);

        switch (numero) {
            case 1:
                return filtroTresCampos(personajes, name, age, movie, ponerCampoNull);
            case 2:
                return filtroNombre(personajes, name, ponerCampoNull);
            case 3:
                return filtroEdad(personajes, age, ponerCampoNull);
            case 4:
                return filtroId(personajes, movie, ponerCampoNull);
            default:
                return personajes;
        }
    }

    private static void validarDatoEntradaFiltro(String name, String age, String movie) {
        List<String> errores = new ArrayList<>();
        if (name != null && validarNombre(name))
            errores.add(POR_FAVOR_INGRESE + "un name " + VALIDO);
        if (age != null && validarEdad(age))
            errores.add(POR_FAVOR_INGRESE + "el age" + VALIDO);
        if (movie != null && validarNumero(movie))
            errores.add(POR_FAVOR_INGRESE + "el movies" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }
}
