package com.arroyo.cine.repository;

import com.arroyo.cine.model.entity.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pelicula_serie WHERE id_pelicula_serie =?1 AND personaje_id = ?2 LIMIT 1", nativeQuery = true)
    void deleteByIdPeliculaSerieAndIdPersonaje(@Param("idPeliculaSerie") Integer idPeliculaSerie, @Param("idPersonaje") Integer idPersonaje);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pelicula_serie_personajes VALUES (?1, ?2)", nativeQuery = true)
    void guardarReferenciaPersonaje(@Param("idPeliculaSerie") Integer idPeliculaSerie, @Param("idPerosnaje") Integer idPerosnaje);
}