package com.arroyo.cine.service;

import com.arroyo.cine.entity.PeliculaSeriePersonaje;
import com.arroyo.cine.repository.PeliculaSeriePersonajeRepository;
import org.springframework.transaction.annotation.Transactional;

public class PeliculaSeriePersonajeService {

    private final PeliculaSeriePersonajeRepository repository;

    public PeliculaSeriePersonajeService(PeliculaSeriePersonajeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(PeliculaSeriePersonaje peliculaSeriePersonaje) {
        repository.save(peliculaSeriePersonaje);
    }

    @Transactional
    public void delete(PeliculaSeriePersonaje peliculaSeriePersonaje) {
        repository.delete(peliculaSeriePersonaje);
    }
}
