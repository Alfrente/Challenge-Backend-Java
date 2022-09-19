package com.arroyo.cine.repository;

import com.arroyo.cine.entity.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {
    PeliculaSerie findByIdPersonaje(Integer idPersonaje);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pelicula_serie WHERE id_pelicula_serie =?1 AND id_personaje = ?2 LIMIT 1", nativeQuery = true)
    void deleteByIdPeliculaSerieAndIdPersonaje(@Param("idPeliculaSerie") Integer idPeliculaSerie, @Param("idPersonaje") Integer idPersonaje);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO pelicula_serie(titulo, imagen, fecha_creacion, califiacion, id_personaje, id_genero)  VALUES (?1, ?2, ?3, ?4, ?5, ?6);", nativeQuery = true)
    void insertByIdPeliculaSerieAndIdPersonaje(@Param("titulo") String titulo, @Param("imagen") String imagen,
                                                        @Param("fecha_creacion") LocalDateTime fecha_creacion, @Param("califiacion") Byte califiacion,
                                                        @Param("id_personaje") Integer id_personaje, @Param("id_genero") Integer id_genero);
}