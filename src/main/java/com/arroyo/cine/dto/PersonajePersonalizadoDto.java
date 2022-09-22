package com.arroyo.cine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PersonajePersonalizadoDto {
    @NotBlank
    @Size(min = 4, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 2)
    private Byte edad;

    @NotBlank
    private Float peso;

    @Null
    @Size(max = 255)
    private String imagen;

    @Null
    private  String historia;

    private List<PeliculaSeriePersolizadaDto> peliculaSeries;

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

    public List<PeliculaSeriePersolizadaDto> getPeliculaSeries() {
        return peliculaSeries;
    }

    public void setPeliculaSeries(List<PeliculaSeriePersolizadaDto> peliculaSeries) {
        this.peliculaSeries = peliculaSeries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonajePersonalizadoDto that = (PersonajePersonalizadoDto) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(edad, that.edad) && Objects.equals(peso, that.peso) && Objects.equals(imagen, that.imagen) && Objects.equals(historia, that.historia) && Objects.equals(peliculaSeries, that.peliculaSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad, peso, imagen, historia, peliculaSeries);
    }
}
