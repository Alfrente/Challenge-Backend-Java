package com.arroyo.cine.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FkPeliculaSeriePersonaje implements Serializable {

    @Serial
    private static final long serialVersionUID = -543941278917545707L;

    @NotNull
    @Column(name = "id_pelicula_serie")
    Integer  idPeliculaSerie;

    @NotNull
    @Column(name = "id_personaje")
    Integer idPersonaje;

    public FkPeliculaSeriePersonaje() {
    }

    public FkPeliculaSeriePersonaje(Integer idPeliculaSerie, Integer idPersonaje) {
        this.idPeliculaSerie = idPeliculaSerie;
        this.idPersonaje = idPersonaje;
    }

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FkPeliculaSeriePersonaje that = (FkPeliculaSeriePersonaje) o;
        return Objects.equals(idPeliculaSerie, that.idPeliculaSerie) && Objects.equals(idPersonaje, that.idPersonaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeliculaSerie, idPersonaje);
    }
}
