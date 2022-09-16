package com.arroyo.cine.dto;

import com.arroyo.cine.entity.PeliculaSerie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;


public class PersonajeDto  {

    private Integer id_personaje;
    @NotBlank
    @Column(length = 55)
    private String nombre;
    @NotBlank
    @Column(length = 2)
    private Byte edad;
    @NotBlank
    private Float peso;
    private String imagen;
    private String historia;

    private List<PeliculaSerie> peliculaSeries;

    public Integer getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(Integer id_personaje) {
        this.id_personaje = id_personaje;
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

    @Override
    public String toString() {
        return "PersonajeDto{" +
                "id_personaje=" + id_personaje +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", imagen='" + imagen + '\'' +
                ", historia='" + historia + '\'' +
                '}';
    }
}
