package com.arroyo.cine.dto;

import com.arroyo.cine.entity.PeliculaSerie;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PersonaDto {
    private int persona;
    @NotBlank
    @Column(length = 55)
    private String nombre;
    @NotBlank
    @Column(length = 2)
    private byte edad;
    @NotBlank
    private float peso;
    private String imagen;
    private String historia;

    private List<PeliculaSerie> peliculaSeries;

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<PeliculaSerie> getPeliculaSeries() {
        return peliculaSeries;
    }

    public void setPeliculaSeries(List<PeliculaSerie> peliculaSeries) {
        this.peliculaSeries = peliculaSeries;
    }
}
