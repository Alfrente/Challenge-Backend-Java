package com.arroyo.cine.dto;

import com.arroyo.cine.entity.PeliculaSerie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

public class PersonajeDto  {

    @NotBlank
    @Size(min = 1)
    private Integer idePersonaje;

    @NotBlank
    @Size(min = 4, max = 50)
    private String nombrePer;

    @NotBlank
    @Size(min = 1, max = 2)
    private Byte edadPer;

    @NotBlank
    private Float pesoPer;

    @Null
    @Size(max = 255)
    private String imagenPer;

    @Null
    private  String historiaPer;

    private List<PeliculaSerieDto> peliculaSeries;

    public Integer getIdePersonaje() {
        return idePersonaje;
    }

    public void setIdePersonaje(Integer idePersonaje) {
        this.idePersonaje = idePersonaje;
    }

    public String getNombrePer() {
        return nombrePer;
    }

    public void setNombrePer(String nombrePer) {
        this.nombrePer = nombrePer;
    }

    public Byte getEdadPer() {
        return edadPer;
    }

    public void setEdadPer(Byte edadPer) {
        this.edadPer = edadPer;
    }

    public Float getPesoPer() {
        return pesoPer;
    }

    public void setPesoPer(Float pesoPer) {
        this.pesoPer = pesoPer;
    }

    public String getImagenPer() {
        return imagenPer;
    }

    public void setImagenPer(String imagenPer) {
        this.imagenPer = imagenPer;
    }

    public String getHistoriaPer() {
        return historiaPer;
    }

    public void setHistoriaPer(String historiaPer) {
        this.historiaPer = historiaPer;
    }

    public List<PeliculaSerieDto> getPeliculaSeries() {
        return peliculaSeries;
    }

    public void setPeliculaSeries(List<PeliculaSerieDto> peliculaSeries) {
        this.peliculaSeries = peliculaSeries;
    }
}
