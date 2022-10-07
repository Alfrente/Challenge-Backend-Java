package com.arroyo.cine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PeliculaSerieDto {

    @Null
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer idPeliculaSerie;

    @NotBlank
    @Size(min = 5, max = 50)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String titulo;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String caratula;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fechaCreacion;

    @Null
    @Size(min = 1, max = 5)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Byte califiacion;

    @NotBlank
    @Size(min = 1)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer idGenero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PersonajeDto> personajes;

    public Integer getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public void setIdPeliculaSerie(Integer idPeliculaSerie) {
        this.idPeliculaSerie = idPeliculaSerie;
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
