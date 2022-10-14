package com.arroyo.cine.model.dto;

import com.arroyo.cine.model.entity.PeliculaSerie;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class GeneroDto {
    @Size(min = 1)
    private String idGenero;

    @NotNull
    @Size(min = 5, max = 50)
    private String nombre;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String imagen;

    @Null
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PeliculaSerie> peliculaSeries;

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneroDto generoDto = (GeneroDto) o;
        return Objects.equals(idGenero, generoDto.idGenero) && Objects.equals(nombre, generoDto.nombre) && Objects.equals(imagen, generoDto.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero, nombre, imagen);
    }
}
