package com.arroyo.cine.repository;

import com.arroyo.cine.entity.PeliculaSeriePersonaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSeriePersonajeRepository extends JpaRepository<PeliculaSeriePersonaje, Integer> {

}
