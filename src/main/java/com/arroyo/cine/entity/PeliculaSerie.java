package com.arroyo.cine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_serie")
    private Integer idPeliculaSerie;

    @NotBlank
    @Size(min = 5, max = 50)
    private String titulo;

    @Null
    private String imagen;

    @NotBlank
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Null
    @Size(min = 1, max = 5)
    private Byte califiacion;

    @NotBlank
    @Size(min = 1)
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    @NotBlank
    @Size(min = 1)
    @Column(name = "id_genero")
    private Integer idGenero;

    @ManyToOne
    @JoinColumn(name = "id_personaje", insertable = false, updatable = false)
    private Personaje personaje;

    public Integer getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public void setIdPeliculaSerie(Integer idPeliculaSerie) {
        this.idPeliculaSerie = idPeliculaSerie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Byte getCalifiacion() {
        return califiacion;
    }

    public void setCalifiacion(Byte califiacion) {
        this.califiacion = califiacion;
    }

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
