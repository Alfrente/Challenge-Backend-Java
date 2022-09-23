package com.arroyo.cine.dto.pelicula_serie;

import com.arroyo.cine.dto.personaje.PersonajeDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class PeliculaSerieDto {

    @NotBlank
    @Size(min = 1)
    private Integer idPeliculaSerie;

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
    private Integer idPersonaje;

    @NotBlank
    @Size(min = 1)
    private Integer idGenero;

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

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
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
        return Objects.equals(idPeliculaSerie, that.idPeliculaSerie) && Objects.equals(titulo, that.titulo) && Objects.equals(caratula, that.caratula) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(califiacion, that.califiacion) && Objects.equals(idPersonaje, that.idPersonaje) && Objects.equals(idGenero, that.idGenero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeliculaSerie, titulo, caratula, fechaCreacion, califiacion, idPersonaje, idGenero);
    }
}
