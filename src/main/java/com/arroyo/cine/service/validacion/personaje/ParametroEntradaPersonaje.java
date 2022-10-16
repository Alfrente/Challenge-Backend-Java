package com.arroyo.cine.service.validacion.personaje;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import com.arroyo.cine.model.dto.PersonajeDto;
import com.arroyo.cine.model.entity.Personaje;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.service.validacion.personaje.Filtro.*;
import static com.arroyo.cine.service.validacion.personaje.ValidarCampoIndividual.*;
import static com.arroyo.cine.util.statico.MensajeError.*;

public class ParametroEntradaPersonaje {

    private ParametroEntradaPersonaje() {
    }

    public static void validarPersonajeDto(String nombre, String edad, String peso) {
        if (nombre == null && edad == null && peso == null)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarPersonajeDto(PersonajeDto dto) {
        if (dto != null && dto.idPersonaje() == null && dto.historia() == null
                && dto.peso() == null && dto.edad() == null && dto.nombre() == null
        )
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }


    public static void compararPersonajeConPersonajeDto(Personaje db, PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (!db.getIdPersonaje().equals(convertirEntero(dto.idPersonaje())))
            errores.add(POR_FAVOR_VERIFIQUE + "el id " + DE_EL + PERSONAJE + ".");
        if (!db.getNombre().equals(dto.nombre()))
            errores.add(POR_FAVOR_VERIFIQUE + "el nombre " + DE_EL + PERSONAJE + ".");
        if (!db.getEdad().equals(convertirByte(dto.edad())))
            errores.add(POR_FAVOR_VERIFIQUE + "la edad " + DE_EL + PERSONAJE + ".");
        if (!db.getPeso().equals(convertirFloat(dto.peso())))
            errores.add(POR_FAVOR_VERIFIQUE + "el peso " + DE_EL + PERSONAJE + ".");
        if (!db.getImagen().equals(dto.imagen()))
            errores.add(POR_FAVOR_VERIFIQUE + "la dirección de la imagen " + DE_EL + PERSONAJE + ".");
        if (!db.getHistoria().equals(dto.historia()))
            errores.add(POR_FAVOR_VERIFIQUE + "la historia " + DE_EL + PERSONAJE + ".");
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    public static void validarDatoRecibido(PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.nombre() == null || validarNombre(dto.nombre()))
            errores.add(POR_FAVOR_INGRESE + "el nombre " + DE_EL + PERSONAJE);
        if (dto.edad() == null || validarEdad(dto.edad()))
            errores.add(POR_FAVOR_INGRESE + "la edad" + VALIDA);
        if (dto.peso() == null || validarPeso(dto.peso()))
            errores.add(POR_FAVOR_INGRESE + "el peso" + VALIDO);
        if (dto.historia() != null && validarHistoria(dto.historia()))
            errores.add(POR_FAVOR_INGRESE + "la historia" + VALIDA);
        if (!errores.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    public static List<Personaje> filtroPersonaje(List<Personaje> personajes, String name, String age, String movie, Boolean ponerCampoNull) {
        if (personajes.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO, MENSAJE, NO_HAY + PERSONAJE + DISPONIBLE, HttpStatus.OK);

        validarFiltro(name, age, movie);
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

    private static void validarFiltro(String name, String age, String movie) {
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
