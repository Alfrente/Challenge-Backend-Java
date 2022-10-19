package com.arroyo.cine.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer idGenero;

    @NotNull
    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    private String nombre;

    @Null
    @Column(unique = true)
    private String imagen;

    @OneToMany(mappedBy = "generoPelicula", fetch = FetchType.LAZY)
    private List<PeliculaSerie> peliculaSerieList;

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
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

    public List<PeliculaSerie> getPeliculaSerieList() {
        return peliculaSerieList;
    }

    public void setPeliculaSerieList(List<PeliculaSerie> peliculaSerieList) {
        this.peliculaSerieList = peliculaSerieList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return Objects.equals(idGenero, genero.idGenero) && Objects.equals(nombre, genero.nombre) && Objects.equals(imagen, genero.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero, nombre, imagen);
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", peliculaSerieList=" + peliculaSerieList +
                '}';
    }
}
