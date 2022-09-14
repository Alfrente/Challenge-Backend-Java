package com.arroyo.cine.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
public class PeliculaSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pelicula_serie ;
    @NotNull
    private Integer id_genero;
    @NotNull
    private Integer id_persona;
    @NotNull
    @Column(length = 50)
    private String titulo;
    private LocalDateTime fecha_creacion;
    @Column(length = 1)
    private Byte califiacion;
}
