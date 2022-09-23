package com.arroyo.cine.dto.personaje;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class PeliculaSeriePersolizadaPDto {
    @NotBlank
    @Size(min = 5, max = 50)
    private String titulo;

    @Null
    private String imagen;

    @NotBlank
    private String fechaCreacion;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
