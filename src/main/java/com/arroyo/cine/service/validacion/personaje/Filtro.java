package com.arroyo.cine.service.validacion.personaje;

import com.arroyo.cine.model.entity.Personaje;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarNumero;
import static com.arroyo.cine.service.validacion.personaje.ValidarCampoIndividual.validarEdad;
import static com.arroyo.cine.service.validacion.personaje.ValidarCampoIndividual.validarNombre;

public class Filtro {

    private Filtro() {
    }

    public static List<Personaje> filtroTresCampos(List<Personaje> personajes, String name, String age, String movie, Boolean ponerCampoNull) {
        return personajes.stream().filter(personaje -> personaje.getNombre().equals(name)
                        && Objects.equals(personaje.getEdad(), convertirByte(age)) && Objects.equals(personaje.getIdPersonaje(),
                        convertirEntero(movie)))
                .map(personaje1 -> ponerNullCampoDto.apply(personaje1, ponerCampoNull)).collect(Collectors.toList());
    }

    public static List<Personaje> filtroNombre(List<Personaje> personajes, String name, Boolean ponerCampoNull) {
        return personajes.stream().filter(personaje -> personaje.getNombre().equals(name))
                .map(personaje1 -> ponerNullCampoDto.apply(personaje1, ponerCampoNull)).collect(Collectors.toList());
    }

    public static List<Personaje> filtroEdad(List<Personaje> personajes, String age, Boolean ponerCampoNull) {
        return personajes.stream().filter(personaje -> Objects.equals(personaje.getEdad(), convertirByte(age)))
                .map(personajeStream -> ponerNullCampoDto.apply(personajeStream, ponerCampoNull)).collect(Collectors.toList());
    }

    public static List<Personaje> filtroId(List<Personaje> personajes, String movie, Boolean ponerCampoNull) {
        return personajes.stream().filter(personaje -> Objects.equals(personaje.getIdPersonaje(), convertirEntero(movie)))
                .map(personaje1 -> ponerNullCampoDto.apply(personaje1, ponerCampoNull)).collect(Collectors.toList());
    }

    public static int numeroFiltro(String name, String age, String movie) {
        if (name == null && age  == null && movie  == null)
            return 0;
        else if (name != null && !validarNombre(name) && age != null && !validarEdad(age) && movie != null && !validarNumero(movie))
            return 1;
        else if (name != null && !validarNombre(name))
            return 2;
        else if (age != null && !validarEdad(age))
            return 3;
        else if (movie != null && !validarNumero(movie))
            return 4;
        return 0;
    }


    private static final BiFunction<Personaje, Boolean, Personaje> ponerNullCampoDto = (personaje, cambiar) -> {
        if (Boolean.TRUE.equals(cambiar)) {
            personaje.setIdPersonaje(null);
            personaje.setHistoria(null);
            personaje.setPeso(null);
            personaje.setPeliculaSeries(null);
            personaje.setEdad(null);
        }
        return personaje;
    };
}
