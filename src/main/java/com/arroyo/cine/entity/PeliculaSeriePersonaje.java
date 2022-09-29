package com.arroyo.cine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pelicula_serie_personajes")
public class PeliculaSeriePersonaje {
    @Id
    @Column(name = "personaje_id", nullable = false)
    Integer personajeId;
    @Column(name = "id_personaje", nullable = false)
    Integer idPersonaje;

    public Integer getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Integer personajeId) {
        this.personajeId = personajeId;
    }

    public Integer getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(Integer idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
}
