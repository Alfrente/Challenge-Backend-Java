package com.arroyo.cine.repository;

import com.arroyo.cine.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Personaje, Integer> {
}
