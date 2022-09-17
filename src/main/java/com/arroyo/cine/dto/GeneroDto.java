package com.arroyo.cine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class GeneroDto {
    @NotBlank
    @Size(min = 1)
    private Integer ideGenero;

    @NotBlank
    @Size(min = 5, max = 50)
    private String nombreGenero;

    @Null
    private String imagenGenero;

    public Integer getIdeGenero() {
        return ideGenero;
    }

    public void setIdeGenero(Integer ideGenero) {
        this.ideGenero = ideGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getImagenGenero() {
        return imagenGenero;
    }

    public void setImagenGenero(String imagenGenero) {
        this.imagenGenero = imagenGenero;
    }
}
