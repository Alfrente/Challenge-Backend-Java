package com.arroyo.cine.service;

import com.arroyo.cine.dto.PeliculaSerieDto;
import com.arroyo.cine.entity.PeliculaSerie;
import com.arroyo.cine.entity.Personaje;
import com.arroyo.cine.exception.custom.pelicula.serie.PeliculaSerieExcepcion;
import com.arroyo.cine.exception.custom.personaje.PersonajeExcepcion;
import com.arroyo.cine.mapper.pelicula_serie.PeliculaSerieMapper;
import com.arroyo.cine.repository.GeneroRepository;
import com.arroyo.cine.repository.PeliculaSeriePersonajeRepository;
import com.arroyo.cine.repository.PeliculaSerieRepository;
import com.arroyo.cine.repository.PersonajeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.arroyo.cine.service.validacion.ValidacionGenerica.convertirEntero;
import static com.arroyo.cine.service.validacion.pelicula.serie.ParametroEntrada.*;
import static com.arroyo.cine.util.statico.RespuestaExcepcion.*;

@Service
public class PeliculaSerieService {
    private final PeliculaSerieRepository repository;
    private final GeneroRepository generoRepository;
    private final PersonajeRepository personajeRepository;

    private final PeliculaSeriePersonajeRepository peliculaSeriePersonajeRepository;
    private final PeliculaSerieMapper mapper;

    public PeliculaSerieService(PeliculaSerieRepository repository, GeneroRepository generoRepository, PersonajeRepository personajeRepository, PeliculaSeriePersonajeRepository peliculaSeriePersonajeRepository, PeliculaSerieMapper mapper) {
        this.repository = repository;
        this.generoRepository = generoRepository;
        this.personajeRepository = personajeRepository;
        this.peliculaSeriePersonajeRepository = peliculaSeriePersonajeRepository;
        this.mapper = mapper;
    }

    public List<PeliculaSerieDto> getAll(String name, Integer genre, String order) {
        return mapper.aListPeliculaSerieDto(filtroPeliculaSerie(repository.findAll(), name, genre, order));
    }

    public PeliculaSerieDto getById(Integer idPeliculaSerie) {
        // validar id string
        return mapper.aPeliculaSerieDto(buscarPeliculaSerieConId(idPeliculaSerie));
    }

    @Transactional
    public PeliculaSerieDto save(PeliculaSerieDto peliculaSerie) {
        verificarPeliculaSerieDto(peliculaSerie);
        verificarParametrosEntradaPeliculaSerie(peliculaSerie);
        verificarParametrosEntradaPersonajes(peliculaSerie.getPersonajes());
        return mapper.aPeliculaSerieDto(repository.save(mapper.aPeliculaSerie(peliculaSerie)));
    }

    @Transactional
    public void savePersonalizado(Integer idPeli, Integer idPersonaje) {
        // validar id string
        Optional<PeliculaSerie> peliculaSerie = Optional.of(buscarPeliculaSerieConId(idPeli));
        Optional<Personaje> personaje = Optional.of(buscarPersonajeConId(idPersonaje));
        peliculaSerie.ifPresent(peliculaSerie1 -> personaje.ifPresent(personaje1 -> {
            peliculaSerie1.setPersonajes(List.of(personaje1));
            repository.save(peliculaSerie1);
        }));
    }

    @Transactional
    public PeliculaSerieDto update(Integer idPeliculaSerie, PeliculaSerieDto peliculaSerieDto) {
        // validar id string
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(idPeliculaSerie);
        return mapper.aPeliculaSerieDto(repository.save(identificarParametroActualizar(peliculaSerie, peliculaSerieDto))); //validar entrada
    }

    @Transactional
    public PeliculaSerieDto delete(PeliculaSerieDto peliculaSerieDto) {
        verificarPeliculaSerieDto(peliculaSerieDto);
        // validar id string
        PeliculaSerie peliculaSerie = buscarPeliculaSerieConId(convertirEntero(peliculaSerieDto.getIdPeliculaSerie()));
        verificarParametrosEntradaPeliculaSerie(peliculaSerieDto);
        validarDatosSonIgual(peliculaSerie, peliculaSerieDto);
        repository.delete(mapper.aPeliculaSerie(setearPersonajesNull(peliculaSerieDto)));
        return mapper.aPeliculaSerieDto(peliculaSerie);
    }

    @Transactional
    public void deletePersonalizado(String idPeliculaSerie, String idPersonaje) {
        // validar id string
        Optional<PeliculaSerie> peliculaSerie = Optional.of(buscarPeliculaSerieConId(convertirEntero(idPeliculaSerie)));
        Optional<Personaje> personaje = Optional.of(buscarPersonajeConId(convertirEntero(idPersonaje)));
        peliculaSerie.ifPresent(peliculaSerie1 -> {
            repository.deleteById(peliculaSerie1.getIdPeliculaSerie());
            personaje.ifPresent(personaje1 -> peliculaSeriePersonajeRepository.deleteById(personaje1.getIdPersonaje()));
        });
    }

    @Transactional
    public PeliculaSerieDto deleteById(String idPeliculaSerie) {
        // validar id string
        Optional<PeliculaSerie> peliculaSerie = Optional.of(buscarPeliculaSerieConId(convertirEntero(idPeliculaSerie)));
        peliculaSerie.ifPresent(peliculaSerie1 -> repository.deleteById(peliculaSerie1.getIdPeliculaSerie()));
        return mapper.aPeliculaSerieDto(peliculaSerie.get());
    }

    private PeliculaSerie buscarPeliculaSerieConId(Integer idPeliculaSerie) {
        return repository.findById(idPeliculaSerie).orElseThrow(() ->
                new PeliculaSerieExcepcion(CODIGO_ERROR, ERROR, LA + PELICULA_SERIE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }

    private void buscarGeneroConId(Integer IdGenero) {
        generoRepository.findById(IdGenero).orElseThrow(() ->
                new PeliculaSerieExcepcion(CODIGO_ERROR, ERROR, POR_FAVOR_VERIFIQUE + "el id " + DE_EL + GENERO + ".", HttpStatus.BAD_REQUEST));
    }

    private Personaje buscarPersonajeConId(Integer idPersonaje) {
        return personajeRepository.findById(idPersonaje).orElseThrow(() ->
                new PersonajeExcepcion(CODIGO_ERROR, ERROR, EL + PERSONAJE + NO_DISPONIBLE, HttpStatus.BAD_REQUEST));
    }
}
