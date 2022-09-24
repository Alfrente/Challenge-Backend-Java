package com.arroyo.cine.dto.personaje;

import com.arroyo.cine.dto.pelicula_serie.PeliculaSerieDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PersonajeDto  {

    @NotBlank
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer idePersonaje;

    @NotBlank
    @Size(min = 4, max = 50)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Byte edad;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Float peso;

    @Null
    @Size(max = 255)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String imagen;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private  String historia;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PeliculaSerieDto> peliculaSeries;

    public Integer getIdePersonaje() {
        return idePersonaje;
    }

    public void setIdePersonaje(Integer idePersonaje) {
        this.idePersonaje = idePersonaje;
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

    public List<PeliculaSerieDto> getPeliculaSeries() {
        return peliculaSeries;
    }

    public void setPeliculaSeries(List<PeliculaSerieDto> peliculaSeries) {
        this.peliculaSeries = peliculaSeries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeDto that = (PersonajeDto) o;
        return Objects.equals(idePersonaje, that.idePersonaje) && Objects.equals(nombre, that.nombre) && Objects.equals(edad, that.edad) && Objects.equals(peso, that.peso) && Objects.equals(imagen, that.imagen) && Objects.equals(historia, that.historia) && Objects.equals(peliculaSeries, that.peliculaSeries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idePersonaje, nombre, edad, peso, imagen, historia, peliculaSeries);
    }
}
