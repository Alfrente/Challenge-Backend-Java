package com.arroyo.cine.service.validacion.imagen;

import com.arroyo.cine.exception.Excepcion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.arroyo.cine.service.validacion.imagen.ValidarImagenEntrada.*;
import static com.arroyo.cine.util.statico.Directorio.GENERO;
import static com.arroyo.cine.util.statico.Directorio.PELICULA_SERIE;
import static com.arroyo.cine.util.statico.Directorio.PERSONAJE;
import static com.arroyo.cine.util.statico.Directorio.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

public class GuardarImagen {

    private GuardarImagen() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GuardarImagen.class);

    public static void crearDirectorio() {
        try {
            Files.createDirectories(PRINCIPAL);
            Files.createDirectories(GENERO);
            Files.createDirectories(PERSONAJE);
            Files.createDirectories(PELICULA_SERIE);
        } catch (IOException ioE) {
            LOGGER.error("Error al crear los directorios", ioE.getCause());
        }
    }


    public static void borrarImagenActualizar(String nombreImageVieja, String nombreImageNueva, int directorio){
        if (!nombreImageNueva.equals(nombreImageVieja)){
            borrarImagen(nombreImageVieja, directorio);
        }
    }

    public static void borrarImagen(String nombreImage, int directorio){
        File directorioImagen = devolverDirectorioImagen(nombreImage, directorio);
        if (directorioImagen.exists()){
            try {
                Files.delete(directorioImagen.toPath());
            } catch (IOException e) {
                LOGGER.error("Error al borrar la imagen", e.getCause());
            }
        }
    }

    public static String guardarImagen(MultipartFile imagen, int directorio) {
        if (imagen != null) {
            validarPesoImagen(imagen);
            validarFormatoImagen(imagen.getOriginalFilename());
            validarExisteImagen(imagen.getOriginalFilename(), directorio);
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaImagen = Paths.get(cambiarDirectorio(directorio), "\\" + imagen.getOriginalFilename());
                Files.write(rutaImagen, bytesImg);
                return imagen.getOriginalFilename();
            } catch (IOException ioE) {
                LOGGER.error("Error al copiar la imagen al directorio", ioE.getCause());
                throw new Excepcion(MENSAJE_CODIGO_ERROR, ERROR, "Al procesar la imagen" + VALIDO, HttpStatus.BAD_REQUEST);
            }
        }
        return null;
    }


}
