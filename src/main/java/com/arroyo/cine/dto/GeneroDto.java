package com.arroyo.cine.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
public class GeneroDto {
    private int id_genero;
    @NotNull
    private int  id_persona;
    @NotNull
    @Column(length = 50)
    private String nombre;
    private String imagen;
}
