package com.arroyo.cine.service.validacion.genero;

import com.arroyo.cine.model.dto.GeneroDto;
import com.arroyo.cine.model.entity.Genero;
import com.arroyo.cine.exception.genero.GeneroExcepcion;
import com.arroyo.cine.exception.genero.GeneroExcepciones;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class ParametroEntradaGenero {

    private ParametroEntradaGenero() {
    }

    public static void validarGeneroDtoEliminar(GeneroDto dto, Genero genero) {
        List<String> excepciones = new ArrayList<>();
        if (dto.getNombreGenero() == null || (!dto.getNombreGenero().equals(genero.getNombre())))
            excepciones.add(POR_FAVOR_VERIFIQUE + "el nombre " + DE_EL + GENERO);
        if (dto.getImagenGenero() == null || (!dto.getImagenGenero().equals(genero.getImagen())))
            excepciones.add(POR_FAVOR_VERIFIQUE + "la dirección de la imagen");
        if (!excepciones.isEmpty())
            throw new GeneroExcepciones(CODIGO_ERROR, ERROR, excepciones, HttpStatus.BAD_REQUEST);
    }

    public static void velidarGeneroDto(GeneroDto dto) {
        if (dto != null && dto.getNombreGenero() == null && dto.getImagenGenero() == null)
            throw new GeneroExcepcion(CODIGO_ERROR, ERROR, INGRESE_DATOS_REQUERIDOS, HttpStatus.BAD_REQUEST);
    }

    public static void validarNombreGeneroDto(String nombre) {
        if (!validarNombreConSinEspacio(nombre))
            throw new GeneroExcepcion(CODIGO_ERROR, ERROR, POR_FAVOR_INGRESE + "el nombre " + DE_EL + GENERO + VALIDO, HttpStatus.BAD_REQUEST);
    }

    public static void validarDireccionImagenGenero(String direccionImagen) {
        if (!validarDireccionImagen(direccionImagen))
            throw new GeneroExcepcion(CODIGO_ERROR, ERROR, INGRESE_DIRECCION_IMAGEN_INCORRECTA, HttpStatus.BAD_REQUEST);
    }
}
