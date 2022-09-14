package com.arroyo.cine.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genero;
    @NotNull
    private Integer  id_persona;
    @NotNull
    @Column(length = 50)
    private String nombre;
    private String imagen;
}
