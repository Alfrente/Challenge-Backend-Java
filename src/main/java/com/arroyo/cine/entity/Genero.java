package com.arroyo.cine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genero;
    @NotBlank
    @Column(length = 50)
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
}
