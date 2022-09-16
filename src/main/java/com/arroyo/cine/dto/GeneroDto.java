package com.arroyo.cine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GeneroDto {
    @NotBlank
    @Size(min = 1)
    private Integer id_genero;
    @NotBlank
    @Size(min = 4, max = 50)
    private String nombre;
    private String imagen;

    @NotBlank
    @Size(min = 1)
    private Integer id_pelicula_serie;

    public Integer getId_genero() {
        return id_genero;
    }

    public void setId_genero(Integer id_genero) {
        this.id_genero = id_genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getId_pelicula_serie() {
        return id_pelicula_serie;
    }

    public void setId_pelicula_serie(Integer id_pelicula_serie) {
        this.id_pelicula_serie = id_pelicula_serie;
    }

    @Override
    public String toString() {
        return "GeneroDto{" +
                "id_genero=" + id_genero +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", id_pelicula_serie=" + id_pelicula_serie +
                '}';
    }
}
