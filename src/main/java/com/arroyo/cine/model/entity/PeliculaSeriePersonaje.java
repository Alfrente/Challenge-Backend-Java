package com.arroyo.cine.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pelicula_serie_personaje")
public class PeliculaSeriePersonaje {

    public PeliculaSeriePersonaje() {
        fkCompuesta = null;
    }

    @EmbeddedId
    private final FkPeliculaSeriePersonaje fkCompuesta;



    public PeliculaSeriePersonaje(FkPeliculaSeriePersonaje fkCompuesta) {
        this.fkCompuesta = fkCompuesta;
    }

    @OneToOne
    @JoinColumn(name = "id_pelicula_serie", insertable = false, updatable = false)
    private PeliculaSerie peliculaSerie;

    @OneToOne
    @JoinColumn(name = "id_personaje", insertable = false, updatable = false)
    private Personaje personaje;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeliculaSeriePersonaje that = (PeliculaSeriePersonaje) o;
        return Objects.equals(fkCompuesta, that.fkCompuesta) && Objects.equals(peliculaSerie, that.peliculaSerie) && Objects.equals(personaje, that.personaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkCompuesta, peliculaSerie, personaje);
    }
}
