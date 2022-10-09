package com.arroyo.cine.service.validacion.personaje;

import com.arroyo.cine.dto.PersonajeDto;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcion;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepciones;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.util.statico.ExprecionRegular.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ParametroEntradaPersonaje {

    private ParametroEntradaPersonaje() {
    }

    public static void validarPersonajeDto(PersonajeDto dto) {
        if (dto != null && dto.getIdePersonaje() == null && dto.getHistoria() == null
                && dto.getImagen() == null && dto.getPeso() == null && dto.getEdad() == null
                && dto.getNombre() == null
        )
            throw new PersonajeExcepcion(CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    private static final BiFunction<Personaje, Boolean, Personaje> setearNull = (personajeFuncion, setear) -> {
        if (Boolean.TRUE.equals(setear)) {
            personajeFuncion.setIdPersonaje(null);
            personajeFuncion.setHistoria(null);
            personajeFuncion.setPeso(null);
            personajeFuncion.setPeliculaSeries(null);
            personajeFuncion.setEdad(null);
        }
        return personajeFuncion;
    };

    public static Personaje verificarDatoModificar(Personaje personaje, PersonajeDto dto) {
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) personaje.setNombre(dto.getNombre());
        if (dto.getEdad() != null && Byte.parseByte(dto.getEdad()) > 0)
            personaje.setEdad(Byte.parseByte(dto.getEdad()));
        if (dto.getPeso() != null && Float.parseFloat(dto.getPeso()) > 0)
            personaje.setPeso(Float.parseFloat(dto.getPeso()));
        if (dto.getImagen() != null && !dto.getImagen().isBlank()) personaje.setImagen(dto.getImagen());
        if (dto.getHistoria() != null && !dto.getHistoria().isBlank()) personaje.setHistoria(dto.getHistoria());
        return personaje;
    }

    public static void verificarPersonajeDBConPersonajeDto(Personaje db, PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (!db.getIdPersonaje().equals(dto.getIdePersonaje()))
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
            throw new PersonajeExcepciones(CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }



    public static boolean validarEdad(String edad) {
        return (!edad.matches(EXPRECION_EDAD));
    }

    public static boolean validarPeso(String peso) {
        return (!peso.matches(EXPRECION_PESO));
    }

    private static boolean validarNombrePersonaje(String nombre) {
        if (nombre.matches(EXPRECION_TEXTO_CON_UN_ESPACIO))
            return false;
        return !nombre.matches(EXPRECION_TEXTO_SIN_ESPACIOS);
    }

    public static void verificarParametrosEntradaPersonaje(PersonajeDto dto) {
        List<String> errores = new ArrayList<>();
        if (dto.getNombre() == null || validarNombrePersonaje(dto.getNombre()))
            errores.add(POR_FAVOR_INGRESE + "el nombre " + DE_EL + PERSONAJE + VALIDO);
        if (dto.getEdad() == null || validarEdad(dto.getEdad()))
            errores.add(POR_FAVOR_INGRESE + "la edad" + VALIDA);
        if (dto.getPeso() == null || validarPeso(dto.getPeso()))
            errores.add(POR_FAVOR_INGRESE + "el peso" + VALIDO);
        if (dto.getImagen() == null || !validarDireccionImagen(dto.getImagen()))
            errores.add(INGRESE_DIRECCION_IMAGEN_INCORRECTA);
        if (dto.getHistoria() == null || dto.getHistoria().isBlank() || !dto.getHistoria().matches(EXPRECION_TEXTO_CON_ESPACIOS))
            errores.add(POR_FAVOR_INGRESE + "la historia" + VALIDA);
        if (!errores.isEmpty())
            throw new PersonajeExcepciones(CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

    public static List<Personaje> filtroPersonaje(List<Personaje> personajes, String name, String age, String movie, Boolean setear) {
        if (personajes.isEmpty())
            throw new PersonajeExcepcion(CODIGO, MENSAJE, NO_HAY + PERSONAJE + DISPONIBLE, HttpStatus.OK);
        validarParametroFiltro(name, age, movie);
        if (name != null && !name.isBlank() && age != null && convertirByte(age) > 0 && movie != null && convertirEntero(movie) > 0)
            return personajes.stream().filter(personajeStream -> personajeStream.getNombre().equals(name)
                            && Objects.equals(personajeStream.getEdad(), convertirByte(age)) && Objects.equals(personajeStream.getIdPersonaje(),
                            convertirEntero(movie)))
                    .map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (name != null && !name.isBlank())
            return personajes.stream().filter(personajeStream -> personajeStream.getNombre().equals(name)).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (age != null && convertirByte(age) > 0)
            return personajes.stream().filter(personajeStream -> Objects.equals(personajeStream.getEdad(), convertirByte(age))).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (movie != null && convertirEntero(movie) > 0)
            return personajes.stream().filter(personajeStream -> Objects.equals(personajeStream.getIdPersonaje(), convertirEntero(movie))).map(personajeStream -> setearNull.apply(personajeStream, setear)).collect(Collectors.toList());
        else if (name == null && age == null && movie == null) return personajes;
        else return new ArrayList<>();
    }

    private static void validarParametroFiltro(String name, String age, String movie) {
        List<String> errores = new ArrayList<>();
        if (name != null && validarNombrePersonaje(name))
            errores.add(POR_FAVOR_INGRESE + "un name " + VALIDO);
        if (age != null && validarEdad(age))
            errores.add(POR_FAVOR_INGRESE + "el age" + VALIDO);
        if (movie != null && !validarStringNumero(movie))
            errores.add(POR_FAVOR_INGRESE + "el movies" + VALIDA);
        if (!errores.isEmpty())
            throw new PersonajeExcepciones(CODIGO_ERROR, ERROR, errores, HttpStatus.BAD_REQUEST);
    }

}
