package com.arroyo.cine.dto.pelicula_serie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class PersonajePersonalizadoPsDto {
    @NotBlank
    @Size(min = 4, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 2)
    private Byte edad;

    @NotBlank
    private Float peso;

    @Null
    @Size(max = 255)
    private String imagen;

    @Null
    private  String historia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getEdad() {
        return edad;
    }

    public void setEdad(Byte edad) {
        this.edad = edad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }
}
