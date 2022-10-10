package com.arroyo.cine.repository;

import com.arroyo.cine.model.entity.PeliculaSeriePersonaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSeriePersonajeRepository extends JpaRepository<PeliculaSeriePersonaje, Integer> {

    @Query(value = "SELECT * FROM pelicula_serie_personaje where id_pelicula_serie=?1 and id_personaje =?2", nativeQuery = true)
    PeliculaSeriePersonaje buscarConIdPeliculaSerieAndIdPersonaje(@Param("IdPeliculaSerie") Integer IdPeliculaSerie, @Param("IdPersonaje") Integer IdPersonaje);



}
