package com.arroyo.cine.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(length = 50, nullable = false)
    @Size(min = 4, max = 50)
    private String nombre;

    @NotNull
    @Column(length = 2, nullable = false)
    @Size(min = 1, max = 2)
    private Byte edad;

    @NotNull
    @Column(nullable = false)
    private Float peso;

    @Null
    @Column(unique = true)
    @Size(max = 255)
    private String imagen;

    @Null
    private  String historia;

    @ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY)
    //@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST})
    private List<PeliculaSerie> peliculaSeries;

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
