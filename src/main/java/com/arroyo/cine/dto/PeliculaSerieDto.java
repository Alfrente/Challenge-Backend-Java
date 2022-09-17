package com.arroyo.cine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class PeliculaSerieDto {

    @NotBlank
    @Size(min = 1)
    private Integer idPeliculaSerie;

    @NotBlank
    @Size(min = 5, max = 50)
    private String tituloPeliSerie;

    @Null
    private String caratula;

    @NotBlank
    private LocalDateTime fechaCreacionPeliSerie;

    @Null
    @Size(min = 1, max = 5)
    private Byte califiacionPeliSerie;

    @NotBlank
    @Size(min = 1)
    private Integer idPersonajePeliSerie;

    @NotBlank
    @Size(min = 1)
    private Integer idGeneroPeliSerie;

    public Integer getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public void setIdPeliculaSerie(Integer idPeliculaSerie) {
        this.idPeliculaSerie = idPeliculaSerie;
    }

    public String getTituloPeliSerie() {
        return tituloPeliSerie;
    }

    public void setTituloPeliSerie(String tituloPeliSerie) {
        this.tituloPeliSerie = tituloPeliSerie;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public LocalDateTime getFechaCreacionPeliSerie() {
        return fechaCreacionPeliSerie;
    }

    public void setFechaCreacionPeliSerie(LocalDateTime fechaCreacionPeliSerie) {
        this.fechaCreacionPeliSerie = fechaCreacionPeliSerie;
    }

    public Byte getCalifiacionPeliSerie() {
        return califiacionPeliSerie;
    }

    public void setCalifiacionPeliSerie(Byte califiacionPeliSerie) {
        this.califiacionPeliSerie = califiacionPeliSerie;
    }

    public Integer getIdPersonajePeliSerie() {
        return idPersonajePeliSerie;
    }

    public void setIdPersonajePeliSerie(Integer idPersonajePeliSerie) {
        this.idPersonajePeliSerie = idPersonajePeliSerie;
    }

    public Integer getIdGeneroPeliSerie() {
        return idGeneroPeliSerie;
    }

    public void setIdGeneroPeliSerie(Integer idGeneroPeliSerie) {
        this.idGeneroPeliSerie = idGeneroPeliSerie;
    }
}
