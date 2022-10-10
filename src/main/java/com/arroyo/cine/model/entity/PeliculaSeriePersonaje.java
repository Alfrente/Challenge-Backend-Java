package com.arroyo.cine.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "pelicula_serie_personaje")
public class PeliculaSeriePersonaje {

    @EmbeddedId
    private FkPeliculaSeriePersonaje fkCompuesta;

    public PeliculaSeriePersonaje() {
    }

    public FkPeliculaSeriePersonaje getFkCompuesta() {
        return fkCompuesta;
    }

    public PeliculaSeriePersonaje(FkPeliculaSeriePersonaje fkCompuesta) {
        this.fkCompuesta = fkCompuesta;
    }

    @OneToOne
    @JoinColumn(name = "id_pelicula_serie", insertable = false, updatable = false)
    private PeliculaSerie peliculaSerie;

    @OneToOne
    @JoinColumn(name = "id_personaje", insertable = false, updatable = false)
    private Personaje personaje;



}
