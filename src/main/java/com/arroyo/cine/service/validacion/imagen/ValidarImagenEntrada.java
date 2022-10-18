package com.arroyo.cine.service.validacion.imagen;

import com.arroyo.cine.exception.Excepcion;
import com.arroyo.cine.util.statico.Directorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.validarFormato;
import static com.arroyo.cine.util.statico.MensajeError.*;

public class ValidarImagenEntrada {
    private ValidarImagenEntrada() {
    }

    public static String cambiarDirectorio(int directorio) {
        if (directorio == 1)
            return Directorio.GENERO.toFile().getAbsolutePath();
        if (directorio == 2)
            return Directorio.PERSONAJE.toFile().getAbsolutePath();
        return Directorio.PELICULA_SERIE.toFile().getAbsolutePath();
    }

    public static void validarPesoImagen(MultipartFile imagen) {
        if (imagen.getSize() > 5242880)
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, "La imagen excede el peso admitido (5MB)" + VALIDO, HttpStatus.BAD_REQUEST);
    }

    public static void validarExisteImagen(String nombreImagen, int directorio) {
        if (devolverDirectorioImagen(nombreImagen, directorio).exists())
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, "la imagen " + nombreImagen + " ya existe", HttpStatus.BAD_REQUEST);
    }

    public static void validarFormatoImagen(String nombreImagen) {
        if (!validarFormato(nombreImagen))
            throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, IMAGEN_FORMATO_INCORRECTO, HttpStatus.BAD_REQUEST);
    }

    public static File devolverDirectorioImagen(String nombreImagen, int directorioIMagen) {
        String directorio;
        if (nombreImagen != null) {
            directorio = cambiarDirectorio(directorioIMagen);
            return buscarImagenDirectorio(new File(directorio, nombreImagen));
        }
        return new File("");
    }

    private static File buscarImagenDirectorio(File file) {
        if (file.exists())
            return file;
        return new File(file.getName());
    }
}
