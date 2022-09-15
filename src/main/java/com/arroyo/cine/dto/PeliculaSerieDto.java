package com.arroyo.cine.dto;

import com.arroyo.cine.entity.Persona;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class PeliculaSerieDto {
    private int id_pelicula_serie ;
    @NotBlank
    private int id_genero;
    @NotBlank
    private int id_persona;
    @NotBlank
    @Column(length = 50)
    private String titulo;
    private LocalDateTime fecha_creacion;
    @Column(length = 1)
    private byte califiacion;

    private Persona persona;
    public int getPelicula_serie() {
        return id_pelicula_serie;
    }

    public void setPelicula_serie(int pelicula_serie) {
        this.id_pelicula_serie = pelicula_serie;
    }

    public int getId_pelicula_serie() {
        return id_pelicula_serie;
    }

    public void setId_pelicula_serie(int id_pelicula_serie) {
        this.id_pelicula_serie = id_pelicula_serie;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public byte getCalifiacion() {
        return califiacion;
    }

    public void setCalifiacion(byte califiacion) {
        this.califiacion = califiacion;
    }
}
