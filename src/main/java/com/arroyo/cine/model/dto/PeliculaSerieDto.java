package com.arroyo.cine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PeliculaSerieDto {
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String idPeliculaSerie;

    @NotNull
    @Size(min = 5, max = 50)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String titulo;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String caratula;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fechaCreacion;

    @Null
    @Size(min = 1, max = 5)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String califiacion;

    @NotNull
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String idGenero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PersonajeDto> personajes;

    public String getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public void setIdPeliculaSerie(String idPeliculaSerie) {
        this.idPeliculaSerie = idPeliculaSerie;
    }

    public String getCalifiacion() {
        return califiacion;
    }

    public void setCalifiacion(String califiacion) {
        this.califiacion = califiacion;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

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

    public List<PersonajeDto> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeDto> personajes) {
        this.personajes = personajes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeliculaSerieDto that = (PeliculaSerieDto) o;
        return Objects.equals(idPeliculaSerie, that.idPeliculaSerie) && Objects.equals(titulo, that.titulo) && Objects.equals(caratula, that.caratula) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(califiacion, that.califiacion) && Objects.equals(idGenero, that.idGenero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeliculaSerie, titulo, caratula, fechaCreacion, califiacion, idGenero);
    }
}
