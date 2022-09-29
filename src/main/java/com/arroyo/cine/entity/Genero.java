package com.arroyo.cine.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Integer idGenero;

    @NotBlank
    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    private String nombre;

    @Null
    private String imagen;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_genero")
    @JoinTable(name = "pelicula_serie", joinColumns = @JoinColumn(name = "id_genero"))
    PeliculaSerie peliculaSerie;

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
}
