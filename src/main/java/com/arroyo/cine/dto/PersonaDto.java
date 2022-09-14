package com.arroyo.cine.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
public class PersonaDto {
    private int id_persona;
    @NotNull
    @Column(length = 55)
    private String nombre;
    @NotNull
    @Column(length = 2)
    private byte edad;
    @NotNull
    private float peso;
    private String imagen;
    private String historia;
}
