package com.arroyo.cine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pelicula_serie ;
    @NotBlank
    @Column(length = 50)
    private String titulo;
    private LocalDateTime fecha_creacion;

    @Column(length = 1)
    private Byte califiacion;

    @NotBlank
    @Size(min = 1)
    private Integer id_personaje;

    @ManyToOne
    @JoinColumn(name = "id_personaje", insertable = false, updatable = false)
    private Personaje personaje;

    public Integer getId_pelicula_serie() {
        return id_pelicula_serie;
    }

    public void setId_pelicula_serie(Integer id_pelicula_serie) {
        this.id_pelicula_serie = id_pelicula_serie;
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

    public Byte getCalifiacion() {
        return califiacion;
    }

    public void setCalifiacion(Byte califiacion) {
        this.califiacion = califiacion;
    }

    public Integer getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(Integer id_personaje) {
        this.id_personaje = id_personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override
    public String toString() {
        return "PeliculaSerie{" +
                "id_pelicula_serie=" + id_pelicula_serie +
                ", titulo='" + titulo + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", califiacion=" + califiacion +
                ", id_personaje=" + id_personaje +
                '}';
    }
}
