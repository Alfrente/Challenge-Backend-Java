package com.arroyo.cine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PersonajeDto  {
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String idePersonaje;

    @NotNull
    @Size(min = 4, max = 50)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 2)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String edad;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String peso;

    @Null
    @Size(max = 255)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String imagen;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private  String historia;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PeliculaSerieDto> peliculaSeries;

    public String getIdePersonaje() {
        return idePersonaje;
    }

    public void setIdePersonaje(String idePersonaje) {
        this.idePersonaje = idePersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
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
        return Objects.equals(idePersonaje, that.idePersonaje) && Objects.equals(nombre, that.nombre) && Objects.equals(edad, that.edad) && Objects.equals(peso, that.peso) && Objects.equals(imagen, that.imagen) && Objects.equals(historia, that.historia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idePersonaje, nombre, edad, peso, imagen, historia);
    }
}
