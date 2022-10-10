package com.arroyo.cine.model.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class FkPeliculaSeriePersonaje implements Serializable {

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

    public Integer getIdPeliculaSerie() {
        return idPeliculaSerie;
    }

    public Integer getIdPersonaje() {
        return idPersonaje;
    }
}
