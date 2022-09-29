package com.arroyo.cine.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "personaje")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personaje")
    private Integer idPersonaje;

    @NotBlank
    @Column(length = 50, nullable = false)
    @Size(min = 4, max = 50)
    private String nombre;

    @NotBlank
    @Column(length = 2, nullable = false)
    @Size(min = 1, max = 2)
    private Byte edad;

    @NotBlank
    @Column(nullable = false)
    private Float peso;

    @Null
    @Size(max = 255)
    private String imagen;

    @Null
    private  String historia;

    @ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private List<PeliculaSerie> peliculaSeries;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinTable(name = "pelicula_serie_personajes", joinColumns = @JoinColumn(name = "id_personaje"), inverseJoinColumns = @JoinColumn(name = "id_personaje", insertable = false, updatable = false))
    private PeliculaSeriePersonaje peliculaSeriePersonaje;

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

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

    public List<PeliculaSerie> getPeliculaSeries() {
        return peliculaSeries;
    }

    public void setPeliculaSeries(List<PeliculaSerie> peliculaSeries) {
        this.peliculaSeries = peliculaSeries;
    }
}
