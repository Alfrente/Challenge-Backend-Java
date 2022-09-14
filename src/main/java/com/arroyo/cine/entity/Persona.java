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
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_persona;
    @NotNull
    @Column(length = 55)
    private String nombre;
    @NotNull
    @Column(length = 2)
    private Byte edad;
    @NotNull
    private Float peso;
    private String imagen;
    private String historia;
}
