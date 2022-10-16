package com.arroyo.cine.util.statico;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Directorio {
    private Directorio() {
    }

    public static final Path PRINCIPAL = Paths.get("src\\main\\resources\\imagen");
    public static final Path GENERO = Paths.get("src\\main\\resources\\imagen\\genero");
    public static final Path PERSONAJE = Paths.get("src\\main\\resources\\imagen\\personaje");
    public static final Path PELICULA_SERIE = Paths.get("src\\main\\resources\\imagen\\pelicula\\serie");
    public static final int DIRECTORIO_GENERO = 1;
    public static final int DIRECTORIO_PERSONAJE = 2;
    //public static final int DIRECTORIO_PELICULA_SERIE = 3;
}
