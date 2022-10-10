package com.arroyo.cine.service;

import com.arroyo.cine.entity.PeliculaSeriePersonaje;
import com.arroyo.cine.repository.PeliculaSeriePersonajeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PeliculaSeriePersonajeService {

    private final PeliculaSeriePersonajeRepository repository;

    public PeliculaSeriePersonajeService(PeliculaSeriePersonajeRepository repository) {
        this.repository = repository;
    }

    public PeliculaSeriePersonaje getByIdPeliculaSerieAndIdPersonaje(Integer idPelicula, Integer idPersonaje){
        return repository.buscarConIdPeliculaSerieAndIdPersonaje(idPelicula, idPersonaje);
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
