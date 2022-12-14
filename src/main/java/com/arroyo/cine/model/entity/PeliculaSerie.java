package com.arroyo.cine.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula_serie")
    private Integer idPeliculaSerie;

    @NotNull
    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    private String titulo;

    @Null
    @Column(unique = true)
    private String imagen;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Null
    @Column(length = 5, nullable = false)
    @Size(min = 1, max = 5)
    private Byte califiacion;

    @Size(min = 1)
    @Column(name = "id_genero", nullable = false)
    private Integer idGenero;

    @ManyToMany(cascade = CascadeType.ALL)
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    @JoinTable(name = "pelicula_serie_personaje", joinColumns = @JoinColumn(name = "id_pelicula_serie"), inverseJoinColumns = @JoinColumn(name = "id_personaje"))
    private List<Personaje> personajes;

    @OneToOne
    @JoinColumn(name = "id_genero", insertable = false, updatable = false)
    private Genero genero;

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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Byte getCalifiacion() {
        return califiacion;
    }

    public void setCalifiacion(Byte califiacion) {
        this.califiacion = califiacion;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
