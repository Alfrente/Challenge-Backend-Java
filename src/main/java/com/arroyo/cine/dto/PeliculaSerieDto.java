package com.arroyo.cine.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class PeliculaSerieDto {
    private int id_pelicula_serie ;
    @NotNull
    private int id_genero;
    @NotNull
    private int id_persona;
    @NotNull
    @Column(length = 50)
    private String titulo;
    private LocalDateTime fecha_creacion;
    @Column(length = 1)
    private byte califiacion;
}
