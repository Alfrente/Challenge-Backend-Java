package com.arroyo.cine.dto.genero;

import com.arroyo.cine.entity.PeliculaSerie;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class GeneroDto {
    @Null
    @Size(min = 1)
    private Integer ideGenero;

    @NotBlank
    @Size(min = 5, max = 50)
    private String nombreGenero;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String imagenGenero;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PeliculaSerie> peliculaSeries;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneroDto generoDto = (GeneroDto) o;
        return Objects.equals(ideGenero, generoDto.ideGenero) && Objects.equals(nombreGenero, generoDto.nombreGenero) && Objects.equals(imagenGenero, generoDto.imagenGenero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ideGenero, nombreGenero, imagenGenero);
    }
}
