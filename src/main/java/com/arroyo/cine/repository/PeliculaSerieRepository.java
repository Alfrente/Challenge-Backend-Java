package com.arroyo.cine.repository;

import com.arroyo.cine.model.entity.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Integer> {

}