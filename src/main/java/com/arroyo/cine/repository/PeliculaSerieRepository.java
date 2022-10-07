package com.arroyo.cine.repository;

import com.arroyo.cine.entity.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {

    @Query(value = "SELECT * FROM pelicula_serie ps RIGHT JOIN personaje p ON ps.personaje_id=p.id_personaje WHERE p.id_personaje=?1 LIMIT 1;", nativeQuery = true)
    PeliculaSerie buscarConIdPersonaje(@Param("idPersonaje") Integer idPersonaje);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pelicula_serie WHERE id_pelicula_serie =?1 AND personaje_id = ?2 LIMIT 1", nativeQuery = true)
    void deleteByIdPeliculaSerieAndIdPersonaje(@Param("idPeliculaSerie") Integer idPeliculaSerie, @Param("idPersonaje") Integer idPersonaje);

    //PeliculaSerie findByIdGenero(Integer idGenero);
}