package com.arroyo.cine.dto.pelicula_serie;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

public class PeliculaSeriePersonalizadoPsDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String titulo;

    @Null
    private String caratula;

    @NotBlank
    private String fechaCreacion;

    @Null
    @Size(min = 1, max = 5)
    private Byte califiacion;

    @NotBlank
    @Size(min = 1)
    private Integer idGenero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PersonajePersonalizadoPsDto> personajes;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
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

    public List<PersonajePersonalizadoPsDto> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajePersonalizadoPsDto> personajes) {
        this.personajes = personajes;
    }
}
