package com.arroyo.cine.service.validacion.genero;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.exception.Excepciones;
import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.model.entity.Genero;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.util.statico.ExprecionRegular.TEXTO_ESPACIO_NUMERO;
import static com.arroyo.cine.util.statico.MensajeError.*;

public class ValidarGeneroEntrada {

    private ValidarGeneroEntrada() {
    }

    public static void validarGeneroDto(GeneroDto dto) {
        if (dto != null && dto.imagen() == null && dto.nombre() == null)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarParametro(String nombreGenero, MultipartFile imagen) {
        if (nombreGenero == null && imagen == null)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarGeneroDtoEliminar(GeneroDto dto, Genero genero) {
        List<String> excepciones = new ArrayList<>();
        if (dto.nombre() == null || (!dto.nombre().equals(genero.getNombre())))
            excepciones.add(POR_FAVOR_VERIFIQUE + "el nombre " + DE_EL + GENERO);
        if (dto.imagen() == null || (!dto.imagen().equals(genero.getImagen())))
            excepciones.add(POR_FAVOR_VERIFIQUE + "la dirección de la imagen");
        if (!excepciones.isEmpty())
            throw new Excepciones(MENSAJE_CODIGO_ERROR, ERROR, excepciones, HttpStatus.BAD_REQUEST);
    }

    public static void verificarHayGenero(List<Genero> generoList) {
        if (generoList.isEmpty())
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, NO_HAY + GENERO + DISPONIBLE, HttpStatus.BAD_REQUEST);
    }

    public static void validarNombreGeneroDto(String nombre) {
        if (nombre != null && !validarNombre(nombre))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, POR_FAVOR_INGRESE + "el nombre " + DE_EL + GENERO + VALIDO, HttpStatus.BAD_REQUEST);
    }

    private static boolean validarNombre(String nombre) {
        return (nombre.matches(TEXTO_ESPACIO_NUMERO));
    }
}
